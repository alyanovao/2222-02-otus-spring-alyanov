package ru.otus.springwork08.service;

import ru.otus.springwork08.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(String id);

    public List<Book> findBooksByParam(String firstName, String kindName);

    List<Book> findAll();

    Book save(Book book);

    Book merge(Book book);

    void deleteBook(Book book);
}
