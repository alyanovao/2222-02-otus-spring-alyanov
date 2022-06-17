package ru.otus.springwork10.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.springwork10.exception.AuthorNotFoundException;
import ru.otus.springwork10.exception.BookNotFoundException;
import ru.otus.springwork10.exception.KindBookNotFoundException;
import ru.otus.springwork10.model.*;
import ru.otus.springwork10.service.AuthorService;
import ru.otus.springwork10.service.BookService;
import ru.otus.springwork10.service.KindBookService;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final KindBookService kindBookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        val books = bookService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
    }

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable(value = "id") Long id) {
        val book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        bookService.deleteBook(book);
        return new ResponseEntity<BookDto>(HttpStatus.OK);
    }

    @PostMapping("/api/book")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookSaveDto book) {
        List<Author> authors = new ArrayList<Author>();
        for (int i = 0; i < book.getAuthorId().size(); i++) {
            authors.add(authorService.findById(book.getAuthorId().get(i)).orElseThrow(AuthorNotFoundException::new));
        }
        List<KindBook> kind = new ArrayList<KindBook>();
        for(int i = 0; i < book.getKindId().size(); i++) {
            kind.add(kindBookService.findById(book.getKindId().get(i)).orElseThrow(KindBookNotFoundException::new));
        }
        final List<Commentary> commentaries;
        if (book.getId() > 0 ) {
            Commentary commentary = new Commentary(0, book.getCommentary(), book.getId());
            commentaries = new ArrayList<>(Arrays.asList(commentary));
        }
        else {
            commentaries = new ArrayList<>();
        }
        val bookSave = new Book(book.getId(), book.getName(), authors, kind, commentaries);
        bookService.save(bookSave);
        return new ResponseEntity<BookDto>(BookDto.toDto(bookSave), HttpStatus.OK);
    }
}
