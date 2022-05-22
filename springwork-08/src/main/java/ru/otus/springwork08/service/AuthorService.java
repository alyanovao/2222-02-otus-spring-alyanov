package ru.otus.springwork08.service;

import ru.otus.springwork08.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(String id);
    List<Author> findAll();
    Author save(Author author);
}
