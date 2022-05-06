package ru.otus.springwork06.dao;

import ru.otus.springwork06.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(long id);
    List<Book> findAll();
    Book save(Book book);

    Book merge(Book book);
    void deleteById(long id);
}
