package ru.otus.springwork08.service;

import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.model.KindBook;

import java.util.List;

//Попытка реализации SRP через патерн фассад
public interface LibraryFacade {
    Author getAuthorById(String id);

    List<Author> getAuthors();

    Author saveAuthor(Author author);

    Author updateAuthor(Author author);

    KindBook getKindById(String id);

    List<KindBook> getKinds();

    KindBook saveKind(KindBook kind);

    KindBook updateKind(KindBook kindBook);

    Book getBookById(String id);

    List<Book> getBooks();

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Book book);

    Commentary getCommentaryById(String id);

    List<Commentary> getBookCommentaries(String id);

    List<Commentary> getAllCommentaries();

    Commentary saveCommentary(Commentary commentary);

    Commentary updateCommentary(Commentary commentary);

    void deleteCommentary(Commentary commentary);
}
