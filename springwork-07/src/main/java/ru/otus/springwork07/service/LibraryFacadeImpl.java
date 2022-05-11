package ru.otus.springwork07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork07.exception.AuthorNotFoundException;
import ru.otus.springwork07.exception.BookNotFoundException;
import ru.otus.springwork07.exception.KindBookNotFoundException;
import ru.otus.springwork07.model.Author;
import ru.otus.springwork07.model.Book;
import ru.otus.springwork07.model.Commentary;
import ru.otus.springwork07.model.KindBook;

import java.util.List;

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
    public Book getBookById(long id) {
        return bookService.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findBooksByParam(String firstName, String kindName) {
        return bookService.findBooksByParam(firstName, kindName);
    }

    @Override
    //@Transactional(readOnly = true)
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
