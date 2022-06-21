package ru.otus.springwork11.controller.reactive;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springwork11.model.*;
import ru.otus.springwork11.repository.AuthorRepository;
import ru.otus.springwork11.repository.BookRepository;
import ru.otus.springwork11.repository.CommentaryRepository;
import ru.otus.springwork11.repository.KindBookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookReactiveRepository {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final KindBookRepository kindBookRepository;
    private final CommentaryRepository commentaryRepository;


    @GetMapping("/api/books")
    public Flux<BookDto> getBooks() {
        return repository.findAll()
                .map(book -> BookDto.toDto(book));
    }

    @GetMapping("/api/book/{id}")
    public Mono<ResponseEntity<Book>> getBook(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/book")
    public  Mono<ResponseEntity<Book>> getBookLibrary() {
        return Mono.just(ResponseEntity.ok().body(new Book()));
    }

    @PostMapping("/api/book")
    public Mono<ResponseEntity<Book>> saveBook(@RequestBody BookSaveDto bookDto) {
        List<Commentary> list = new ArrayList<>();
        if (bookDto.getCommentary() != null) {
            val commentary = new Commentary(bookDto.getCommentary(), bookDto.getId());
            commentaryRepository.save(commentary)
                    .map(c -> list.add(c))
                    .subscribe();
        }

        return Mono.just(new Book())
                .map(book -> {
                    book.setId(bookDto.getId());
                    book.setName(bookDto.getName());
                    book.setAuthors(new ArrayList<>());
                    book.setKind(new ArrayList<>());
                    book.setCommentary(list);
                    return book;
                })
                .flatMapMany(book -> {
                    return authorRepository.findAllById(bookDto.getAuthorId())
                            .map(author -> {
                                book.getAuthors().add(author);
                                return book;
                            });
                }).last()
                .flatMapMany(book -> {
                    return kindBookRepository.findAllById(bookDto.getKindId())
                            .map(kind -> {
                                book.getKind().add(kind);
                                return book;
                            });
                }).last()
                .flatMap(book -> {
                    return repository.save(book).map(res -> new ResponseEntity<>(res, HttpStatus.OK));
                })
                .defaultIfEmpty(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));

        /*
        List<Author> authors = new ArrayList<>();
        authorRepository.findAllById(bookDto.getAuthorId())
                .map(author -> authors.add(author))
                .blockLast();

        List<KindBook> kinds = new ArrayList<>();
        kindBookRepository.findAllById(bookDto.getKindId())
                .map(kind -> kinds.add(kind))
                .blockLast();

        List<Commentary> commentary = new ArrayList<>();
        if (bookDto.getCommentary() != null) {
            commentary.add(new Commentary(bookDto.getCommentary(), bookDto.getId()));
            commentaryRepository.saveAll(commentary)
                    .blockLast();
        }

        val book = new Book(bookDto.getId(), bookDto.getName(), authors, kinds, commentary);
        return repository.save(book)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        */
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String id) {
        return repository.deleteById(id)
                .map(ResponseEntity::ok);
    }
}
