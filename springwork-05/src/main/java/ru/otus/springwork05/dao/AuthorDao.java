package ru.otus.springwork05.dao;

import ru.otus.springwork05.model.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(long id);
    void insert(Author author);
    List<Author> getAll();

    long getEmptyId();
}
