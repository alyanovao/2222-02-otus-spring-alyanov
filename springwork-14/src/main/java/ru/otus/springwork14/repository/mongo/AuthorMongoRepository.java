package ru.otus.springwork14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springwork14.model.AuthorDto;

public interface AuthorMongoRepository extends MongoRepository<AuthorDto, String> {
}
