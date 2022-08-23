package ru.otus.libraryserviceslave.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryserviceslave.exception.BookNotFoundException;
import ru.otus.libraryserviceslave.model.Author;
import ru.otus.libraryserviceslave.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        val authors = authorService.findAll();
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }
}