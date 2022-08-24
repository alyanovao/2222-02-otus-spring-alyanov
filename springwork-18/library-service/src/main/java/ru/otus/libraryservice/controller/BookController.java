package ru.otus.libraryservice.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.libraryservice.availible.BookCircuitBreakerService;
import ru.otus.libraryservice.availible.BookSupplierService;
import ru.otus.libraryservice.exception.BookNotFoundException;
import ru.otus.libraryservice.model.Book;
import ru.otus.libraryservice.model.BookDto;
import ru.otus.libraryservice.model.BookModelDto;
import ru.otus.libraryservice.service.AuthorService;
import ru.otus.libraryservice.service.BookService;
import ru.otus.libraryservice.service.KindBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Controller
public class BookController {
    private final Supplier<List<Book>> decorateBookService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final KindBookService kindBookService;

    public BookController(BookSupplierService decorateBookSupplier,
                          BookService bookService,
                          AuthorService authorService,
                          KindBookService kindBookService) {
        this.decorateBookService = decorateBookSupplier.getBooksSupplier();
        this.bookService = bookService;
        this.authorService = authorService;
        this.kindBookService = kindBookService;
    }

    @GetMapping("/book")
    public String getBook(Model model) {
        List<BookDto> bookDto = new ArrayList<>();
        List<Book> books = null;
        try {
            books = decorateBookService.get();
            for(Book book : books) {
                bookDto.add(BookDto.toDto(book));
            }
        }
        catch (Exception e) {
            log.error("Could not get book");
        }
        model.addAttribute("books", bookDto);
        return "book";
    }

    @GetMapping("/bookEdit")
    public String editBookPage(@RequestParam(defaultValue = "0", name = "id") long id, Model model) {
        if(id == 0) {
            model.addAttribute("book", new BookDto());
        }
        else {
            val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
            val bookDto = BookModelDto.toDto(book);
            model.addAttribute("isEdit", true);
            model.addAttribute("book", bookDto);
        }

        val authorList = authorService.findAll();
        model.addAttribute("authors", authorList);
        model.addAttribute("kindList", kindBookService.findAll());
        return "bookEdit";
    }

    @PostMapping("/bookEdit")
    public  String editBook(@ModelAttribute("book") BookModelDto book) {
        val book1 = BookModelDto.fromDto(book);
        bookService.save(book1);
        return "redirect:/book";
    }

    @GetMapping("/bookDelete")
    public String deleteBookPage(Model model, @RequestParam(name = "id") long id) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        model.addAttribute("book", book);
        return "bookDelete";
    }

    @PostMapping("/bookDelete")
    public String deleteBook(long id) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        bookService.deleteBook(book);
        return "redirect:/book";
    }
}
