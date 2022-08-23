package ru.otus.libraryserviceslave.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryserviceslave.exception.AuthorNotFoundException;
import ru.otus.libraryserviceslave.exception.BookNotFoundException;
import ru.otus.libraryserviceslave.exception.KindBookNotFoundException;
import ru.otus.libraryserviceslave.model.*;
import ru.otus.libraryserviceslave.service.AuthorService;
import ru.otus.libraryserviceslave.service.BookService;
import ru.otus.libraryserviceslave.service.KindBookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getBooks() {
        val books = bookService.findAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
}
