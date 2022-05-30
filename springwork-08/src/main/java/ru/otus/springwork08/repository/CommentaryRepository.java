package ru.otus.springwork08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springwork08.model.Commentary;

public interface CommentaryRepository extends MongoRepository<Commentary, Long>, CommentaryRepositoryCustom {
}
