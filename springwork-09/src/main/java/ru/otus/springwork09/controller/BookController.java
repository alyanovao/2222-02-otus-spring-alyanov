package ru.otus.springwork09.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.springwork09.exception.AuthorNotFoundException;
import ru.otus.springwork09.exception.BookNotFoundException;
import ru.otus.springwork09.model.*;
import ru.otus.springwork09.service.AuthorService;
import ru.otus.springwork09.service.BookService;
import ru.otus.springwork09.service.KindBookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final KindBookService kindBookService;

    @GetMapping("/book")
    public String getBook(Model model) {
        val books = bookService.findAll();
        List<BookDto> bookDto = new ArrayList<>();
        for(Book book : books) {
            bookDto.add(BookDto.toDto(book));
        }
        model.addAttribute("books", bookDto);
        return "book";
    }

    @GetMapping("/bookEdit")
    public String editBookPage(@RequestParam(required = false, name = "id") long id, Model model) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        model.addAttribute("book", book);

        val authorList = authorService.findAll();
        List<AuthorDto> authors = new ArrayList<>();
        for (Author author : authorList) {
            authors.add(AuthorDto.toDto(author));
        }
        AuthorDtoList authorDto = new AuthorDtoList();
        authorDto.setAuthorList(authors);
        model.addAttribute("authors", authors);
        model.addAttribute("kindList", kindBookService.findAll());
        return "bookEdit";
    }

    @PostMapping("/bookEdit")
    public  String editBook(@ModelAttribute("book") Book book, @ModelAttribute("author") AuthorDto author) {//TODO - не работает множественная выборка @ModelAttribute("authorDto") AuthorDtoList authorDto) {
        val newAuthor = authorService.findById(author.getId()).orElseThrow(AuthorNotFoundException::new);
        val bookSave = new Book(book.getId(), book.getName(), new ArrayList<>(Arrays.asList(newAuthor)), new ArrayList<>(), new ArrayList<>());
        bookService.save(bookSave);
        return "redirect:/book";
    }

    @GetMapping("/bookAdd")
    public String addBookPage(Model model) {
        val book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authorList", authorService.findAll());
        model.addAttribute("kindList", kindBookService.findAll());
        return "bookAdd";
    }
    @PostMapping("/bookAdd")
    public String addBook(Book book) {
        val bookSave = new Book(book.getId(), book.getName(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        bookService.save(bookSave);
        return "redirect:/book";
    }
    @GetMapping("/bookDelete")
    public String deleteBookPage(Model model, @RequestParam(name = "id") long id) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        model.addAttribute("book", book);
        return "bookDelete";
    }

    @PostMapping("/bookDelete")
    private String deleteBook(long id) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        bookService.deleteBook(book);
        return "redirect:/book";
    }
}
