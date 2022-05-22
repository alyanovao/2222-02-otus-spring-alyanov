package ru.otus.springwork08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springwork08.model.Book;

public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {
}
