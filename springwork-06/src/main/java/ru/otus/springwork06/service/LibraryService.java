package ru.otus.springwork06.service;

import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;
import ru.otus.springwork06.model.KindBook;

import java.util.List;

public interface LibraryService {
    Author getAuthorById(long id);

    List<Author> getAuthors();

    Author saveAuthor(Author author);

    KindBook getKindById(long id);

    List<KindBook> getKinds();

    KindBook saveKind(KindBook kind);

    Book getBookById(long id);

    List<Book> getBooks();

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteById(long id);

    Commentary getCommentaryById(long id);

    List<Commentary> getCommentariesById(long id);

    Commentary saveCommentary(Commentary commentary);

    Commentary updateCommentary(Commentary commentary);

    void deleteCommentary(long id);
}
