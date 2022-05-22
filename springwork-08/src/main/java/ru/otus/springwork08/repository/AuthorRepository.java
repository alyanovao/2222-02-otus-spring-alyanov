package ru.otus.springwork08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springwork08.model.Author;

public interface AuthorRepository extends MongoRepository<Author, Long>, AuthorRepositoryCustom {
}
