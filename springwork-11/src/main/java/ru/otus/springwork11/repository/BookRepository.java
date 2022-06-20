package ru.otus.springwork11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.springwork11.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
