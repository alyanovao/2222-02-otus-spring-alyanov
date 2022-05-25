package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork08.exception.AuthorNotFoundException;
import ru.otus.springwork08.exception.BookNotFoundException;
import ru.otus.springwork08.exception.KindBookNotFoundException;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.model.KindBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryFacadeImpl implements LibraryFacade {

    private final AuthorService authorService;
    private final KindBookService kindBookService;
    private final BookService bookService;
    private final CommentaryService commentaryService;

    @Override
    public Author getAuthorById(String id) {
        var author = authorService.findById(id);
        return author.orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        return authorService.save(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        return authorService.merge(author);
    }

    @Override
    public KindBook getKindById(String id) {
        return kindBookService.findById(id).orElseThrow(KindBookNotFoundException::new);
    }

    @Override
    public List<KindBook> getKinds() {
        return kindBookService.findAll();
    }

    @Override
    @Transactional
    public KindBook saveKind(KindBook kind) {
        return kindBookService.save(kind);
    }

    @Override
    @Transactional
    public KindBook updateKind(KindBook kindBook) {
        return kindBookService.merge(kindBook);
    }

    @Override
    public Book getBookById(String id) {
        return bookService.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> getBooks() {
        return bookService.findAll();
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
    public Commentary getCommentaryById(String id) {
        return commentaryService.getById(id);
    }

    @Override
    public List<Commentary> getBookCommentaries(String id) {
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
    public void deleteCommentary(Commentary commentary) {
        val book = bookService.findById(commentary.getBookId()).orElseThrow(BookNotFoundException::new);
        book.setCommentary(new ArrayList<>());
        bookService.save(book);
        commentaryService.delete(commentary);
    }
}
