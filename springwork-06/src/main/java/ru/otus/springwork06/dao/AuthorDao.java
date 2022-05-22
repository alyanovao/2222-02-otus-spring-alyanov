package ru.otus.springwork06.dao;

import ru.otus.springwork06.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);
    List<Author> findAll();
    Author save(Author author);
}
