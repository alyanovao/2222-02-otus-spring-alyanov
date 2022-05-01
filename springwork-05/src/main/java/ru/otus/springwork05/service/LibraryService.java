package ru.otus.springwork05.service;

import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

public interface LibraryService {
    Book getBookById(long id);
    Book getBookByName(String name);
    List<Book> getBooks();
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(long id);
    void addAuthor(Author author);
    List<Author> getAuthors();
    Author getAuthorById(long id);
    long getEmptyAuthorId();
    long getEmptyKindId();
    long getEmptyBookId();
    void addKindBook(KindBook kind);
    List<KindBook> getKindBooks();
    KindBook getKindBookById(Long id);
    void deleteKindBook(long id);
}
