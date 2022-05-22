package ru.otus.springwork08.processor;

import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.model.KindBook;

import java.util.List;

public interface PrintService {
    void printBook(Book book);

    void printBook(List<Book> book);

    void printAuthor(Author author);

    void printAuthor(List<Author> author);

    void printKindBook(List<KindBook> kindBooks);

    void printCommentaries(List<Commentary> commentary);
}
