package ru.otus.libraryservice.availible;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.libraryservice.model.Author;
import ru.otus.libraryservice.model.KindBook;
import ru.otus.libraryservice.service.AuthorService;
import ru.otus.libraryservice.service.KindBookService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class KindBookSupplierService {

    private final KindBookService kindBookService;

    private final CircuitBreaker circuitBreaker;

    private final KindBookCircuitBreakerService service;

    public Supplier<List<KindBook>> getKindBookSupplier() {

        Supplier<List<KindBook>> authorSupplier = () -> kindBookService.findAll();
        return Decorators
                .ofSupplier(authorSupplier)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(Arrays.asList(CallNotPermittedException.class),
                        e -> service.findAll())
                .decorate();
    }
}
