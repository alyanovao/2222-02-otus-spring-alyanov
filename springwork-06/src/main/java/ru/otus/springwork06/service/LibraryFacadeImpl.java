package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.exception.AuthorNotFoundException;
import ru.otus.springwork06.exception.BookNotFoundException;
import ru.otus.springwork06.exception.KindBookNotFoundException;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;
import ru.otus.springwork06.model.KindBook;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LibraryFacadeImpl implements LibraryFacade {

    private final AuthorService authorService;
    private final KindBookService kindBookService;
    private final BookService bookService;
    private final CommentaryService commentaryService;

    @Override
    public Author getAuthorById(long id) {
        var author = authorService.findById(id);
        return author.orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    //@Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        return authorService.save(author);
    }

    @Override
    //@Transactional(readOnly = true)
    public KindBook getKindById(long id) {
        return kindBookService.findById(id).orElseThrow(KindBookNotFoundException::new);
    }

    @Override
    //@Transactional(readOnly = true)
    public List<KindBook> getKinds() {
        return kindBookService.findAll();
    }

    @Override
    @Transactional
    public KindBook saveKind(KindBook kind) {
        return kindBookService.save(kind);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        Book book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        Hibernate.initialize(book.getCommentary());
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        List<Book> books =  bookService.findAll();
        books.stream().map(book -> {
            Hibernate.initialize(book.getCommentary());
            return book;
        }).collect(Collectors.toList());
        return books;
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookService.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookService.merge(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookService.deleteBook(book);
    }

    @Override
    //@Transactional(readOnly = true)
    public Commentary getCommentaryById(long id) {
        return commentaryService.getById(id);
    }

    @Override
    public List<Commentary> getBookCommentaries(long id) {
        return bookService.findById(id).orElseThrow(BookNotFoundException::new).getCommentary();
    }

    @Override
    public List<Commentary> getAllCommentaries() {
        return commentaryService.getAll();
    }

    @Override
    @Transactional
    public Commentary saveCommentary(Commentary commentary) {
        return commentaryService.save(commentary);
    }

    @Override
    @Transactional
    public Commentary updateCommentary(Commentary commentary) {
        return commentaryService.update(commentary);
    }

    @Override
    @Transactional
    public void deleteCommentary(long id) {
        commentaryService.delete(id);
    }
}
