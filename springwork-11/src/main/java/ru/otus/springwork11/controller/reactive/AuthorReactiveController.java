package ru.otus.springwork11.controller.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springwork11.model.Author;
import ru.otus.springwork11.repository.AuthorRepository;

@RequiredArgsConstructor
@RestController
public class AuthorReactiveController {
    private final AuthorRepository repo;

    @GetMapping("/api/authors")
    public Flux<Author> getAuthors() {
        return repo.findAll();
    }

    @GetMapping("/api/author/{id}")
    public Mono<ResponseEntity<Author>> getAuthor(@PathVariable("id") String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/author")
    public Author getAuthor() {
        return new Author();
    }

    @PostMapping("/api/author")
    public Mono<ResponseEntity<Author>> saveAuthor(@RequestBody Author author) {
        return repo.save(author)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
