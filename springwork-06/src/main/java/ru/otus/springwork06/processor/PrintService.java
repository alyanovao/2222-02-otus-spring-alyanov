package ru.otus.springwork06.processor;

import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;
import ru.otus.springwork06.model.KindBook;

import java.util.List;

public interface PrintService {
    void printBook(Book book);
    void printBook(List<Book> bookView);
    void printAuthor(Author author);
    void printAuthor(List<Author> author);

    void printKindBook(List<KindBook> kindBooks);

    void printCommentaries(List<Commentary> commentary);
}
