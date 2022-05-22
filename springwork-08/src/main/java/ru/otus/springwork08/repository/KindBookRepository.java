package ru.otus.springwork08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springwork08.model.KindBook;

public interface KindBookRepository extends MongoRepository<KindBook, Long>, KindBookRepositoryCustom {
}
