package ru.otus.springwork05.dao;

import ru.otus.springwork05.model.Book;

import java.util.List;

public interface BookDao {
    Book getById(long id);
    Book getByName(String name);
    void insert(Book book);
    void update(Book book);
    void delete(long id);
    long getEmptyId();
    List<Book> getAll();
}
