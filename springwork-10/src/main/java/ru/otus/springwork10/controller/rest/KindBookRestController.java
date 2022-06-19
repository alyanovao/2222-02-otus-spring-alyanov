package ru.otus.springwork10.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.springwork10.model.Author;
import ru.otus.springwork10.model.KindBook;
import ru.otus.springwork10.service.AuthorService;
import ru.otus.springwork10.service.KindBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KindBookRestController {
    private final AuthorService kindBookService;

    @GetMapping("/api/kindBooks")
    public ResponseEntity<List<Author>> getKindBooks() {
        return new ResponseEntity<List<Author>>(kindBookService.findAll(), HttpStatus.OK);
    }
}
