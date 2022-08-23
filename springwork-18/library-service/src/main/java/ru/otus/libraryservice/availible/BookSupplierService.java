package ru.otus.libraryservice.availible;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.libraryservice.model.Book;
import ru.otus.libraryservice.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BookSupplierService {

    private final BookService bookService;

    private final CircuitBreaker circuitBreaker;

    private final BookCircuitBreakerService service;

    public Supplier<List<Book>> getBooksSupplier() {

        Supplier<List<Book>> bookApplier = () -> bookService.findAll();
        return Decorators
                .ofSupplier(bookApplier)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(Arrays.asList(CallNotPermittedException.class),
                        e -> service.findAll())
                .decorate();
    }
}
