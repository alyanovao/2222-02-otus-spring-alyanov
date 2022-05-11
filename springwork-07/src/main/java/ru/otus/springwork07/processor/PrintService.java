package ru.otus.springwork07.processor;

import ru.otus.springwork07.model.Author;
import ru.otus.springwork07.model.Book;
import ru.otus.springwork07.model.Commentary;
import ru.otus.springwork07.model.KindBook;

import java.util.List;

public interface PrintService {
    void printBook(Book book);

    void printBook(List<Book> bookView);

    void printAuthor(Author author);

    void printAuthor(List<Author> author);

    void printKindBook(List<KindBook> kindBooks);

    void printCommentaries(List<Commentary> commentary);
}
