package ru.otus.springwork10.controller.view;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.springwork10.exception.BookNotFoundException;
import ru.otus.springwork10.model.Book;
import ru.otus.springwork10.model.BookDto;
import ru.otus.springwork10.model.BookModelDto;
import ru.otus.springwork10.service.AuthorService;
import ru.otus.springwork10.service.BookService;
import ru.otus.springwork10.service.KindBookService;

import java.util.ArrayList;
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
        for (Book book : books) {
            bookDto.add(BookDto.toDto(book));
        }
        model.addAttribute("books", bookDto);
        return "book";
    }

    @GetMapping("/book/edit/{id}")
    public String editBookPage(@PathVariable(name = "id") long id, Model model) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        val bookDto = BookModelDto.toDto(book);
        model.addAttribute("book", bookDto);

        val authorList = authorService.findAll();
        model.addAttribute("authors", authorList);
        model.addAttribute("kindList", kindBookService.findAll());
        return "bookEdit";
    }

    @GetMapping("/book/edit")
    public String saveBookPage(Model model) {
        val bookDto = new BookModelDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("kindList", kindBookService.findAll());
        return "bookEdit";
    }
}