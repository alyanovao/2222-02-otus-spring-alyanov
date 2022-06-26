package ru.otus.springwork11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.springwork11.model.Commentary;

public interface CommentaryRepository extends ReactiveMongoRepository<Commentary, String> {
}
