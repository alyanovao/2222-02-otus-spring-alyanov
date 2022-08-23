package ru.otus.libraryserviceslave.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.libraryserviceslave.model.KindBook;
import ru.otus.libraryserviceslave.service.KindBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KindBookRestController {
    private final KindBookService kindBookService;

    @GetMapping("/api/kindBooks")
    public ResponseEntity<List<KindBook>> getKindBooks() {
        return new ResponseEntity<List<KindBook>>(kindBookService.findAll(), HttpStatus.OK);
    }
}
