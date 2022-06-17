package ru.otus.springwork10.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.springwork10.exception.BookNotFoundException;
import ru.otus.springwork10.model.Author;
import ru.otus.springwork10.service.AuthorService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<List<Author>>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/author/{id}"})
    public ResponseEntity<Author> getAuthor(@PathVariable("id") long id) {
        val author = authorService.findById(id).orElseThrow(BookNotFoundException::new);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping("/api/author")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        authorService.save(author);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }
}