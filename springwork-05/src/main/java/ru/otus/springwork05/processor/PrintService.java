package ru.otus.springwork05.processor;

import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

public interface PrintService {
    void printBook(Book book);
    void printBook(List<Book> bookView);
    void printAuthor(Author author);
    void printAuthor(List<Author> author);

    void printKindBook(List<KindBook> kindBooks);
}
