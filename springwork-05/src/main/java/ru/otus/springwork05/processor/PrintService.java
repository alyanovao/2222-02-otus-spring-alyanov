package ru.otus.springwork05.processor;

import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.BookView;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

public interface PrintService {
    void printBookView(BookView bookView);
    void printBookView(List<BookView> bookView);
    void printAuthor(Author author);
    void printAuthor(List<Author> author);

    void printKindBook(List<KindBook> kindBooks);
}
