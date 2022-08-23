package ru.otus.libraryservice.availible;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.libraryservice.model.Author;
import ru.otus.libraryservice.service.AuthorService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AuthorSupplierService {

    private final AuthorService authorService;

    private final CircuitBreaker circuitBreaker;

    private final AuthorCircuitBreakerService service;

    public Supplier<List<Author>> getAuthorsSupplier() {

        Supplier<List<Author>> authorSupplier = () -> authorService.findAll();
        return Decorators
                .ofSupplier(authorSupplier)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(Arrays.asList(CallNotPermittedException.class),
                        e -> service.findAll())
                .decorate();
    }
}
