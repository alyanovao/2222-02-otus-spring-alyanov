package ru.otus.springwork11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.springwork11.model.KindBook;

public interface KindBookRepository extends ReactiveMongoRepository<KindBook, String> {
}
