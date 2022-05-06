package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.dao.AuthorDao;
import ru.otus.springwork06.dao.BookDao;
import ru.otus.springwork06.dao.CommentaryDao;
import ru.otus.springwork06.dao.KindBookDao;
import ru.otus.springwork06.exception.AuthorNotFoundException;
import ru.otus.springwork06.exception.BookNotFoundException;
import ru.otus.springwork06.exception.KindBookNotFoundException;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;
import ru.otus.springwork06.model.KindBook;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final KindBookDao kindBookDao;
    private final BookDao bookDao;
    private final CommentaryDao commentaryDao;

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(long id) {
        var author =  authorDao.findById(id);
        return author.orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorDao.findAll();
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        return authorDao.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public KindBook getKindById(long id) {
        return kindBookDao.findById(id).orElseThrow(KindBookNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KindBook> getKinds() {
        return kindBookDao.findAll();
    }

    @Override
    @Transactional
    public KindBook saveKind(KindBook kind) {
        return kindBookDao.save(kind);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        return bookDao.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return bookDao.findAll();
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookDao.merge(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Commentary getCommentaryById(long id) {
        return commentaryDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Commentary> getCommentariesById(long id) {
        return commentaryDao.getAllByBookId(id);
    }

    @Override
    @Transactional
    public Commentary saveCommentary(Commentary commentary) {
        return commentaryDao.save(commentary);
    }

    @Override
    @Transactional
    public Commentary updateCommentary(Commentary commentary) {
        return commentaryDao.update(commentary);
    }

    @Override
    @Transactional
    public void deleteCommentary(long id) {
        commentaryDao.delete(id);
    }
}
