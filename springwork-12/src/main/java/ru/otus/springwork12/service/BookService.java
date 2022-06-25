package ru.otus.springwork12.service;

import ru.otus.springwork12.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    public List<Book> findBooksByParam(String firstName, String kindName);

    List<Book> findAll();

    Book save(Book book);

    Book merge(Book book);

    void deleteBook(Book book);
}
