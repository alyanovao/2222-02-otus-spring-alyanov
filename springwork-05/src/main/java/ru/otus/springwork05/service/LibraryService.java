package ru.otus.springwork05.service;

import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.BookView;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

public interface LibraryService {
    BookView getBookById(long id);
    BookView getBookByName(String name);
    List<BookView> getBooks();
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(long id);
    void addAuthor(Author author);
    List<Author> getAuthors();
    long getEmptyAuthorId();
    long getEmptyKindId();
    long getEmptyBookId();
    void addKindBook(KindBook kind);
    List<KindBook> getKindBooks();
    void deleteKindBook(long id);
}
