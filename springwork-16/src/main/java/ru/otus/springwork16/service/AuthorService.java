package ru.otus.springwork16.service;

import ru.otus.springwork16.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(long id);
    List<Author> findAll();
    Author save(Author author);
}
