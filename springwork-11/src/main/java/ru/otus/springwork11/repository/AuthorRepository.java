package ru.otus.springwork11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.springwork11.model.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
