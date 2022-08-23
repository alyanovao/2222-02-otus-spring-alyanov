package ru.otus.libraryserviceslave.service;

import ru.otus.libraryserviceslave.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(long id);
    List<Author> findAll();
    Author save(Author author);
}
