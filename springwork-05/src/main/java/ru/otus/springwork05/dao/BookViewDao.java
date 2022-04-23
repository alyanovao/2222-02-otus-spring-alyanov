package ru.otus.springwork05.dao;

import ru.otus.springwork05.model.BookView;

import java.util.List;

public interface BookViewDao {
    BookView getBookById(long id);
    BookView getBookByName(String name);
    List<BookView> getAllBooks();
}
