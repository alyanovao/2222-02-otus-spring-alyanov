package ru.otus.springwork11.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.otus.springwork11.model.KindBook;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class KindBookRepositoryTest {

    @Autowired
    private KindBookRepository kindBookRepository;

    @Test
    void getKindBook() {
        Flux<KindBook> kind = kindBookRepository.findAll();

        StepVerifier
                .create(kind)
                .assertNext(s -> assertNotNull(s.getId()))
                .assertNext(s -> assertNotNull(s.getId()))
                .assertNext(s -> assertNotNull(s.getId()))
                .expectComplete()
                .verify();
    }

}