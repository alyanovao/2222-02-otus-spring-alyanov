package ru.otus.springwork11.controller.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springwork11.model.KindBook;
import ru.otus.springwork11.repository.KindBookRepository;

@RequiredArgsConstructor
@RestController
public class KindBookReactiveController {

    private final KindBookRepository repo;

    @GetMapping("/api/kindBooks")
    public Flux<KindBook> getKindBook() {
        return repo.findAll();
    }
}
