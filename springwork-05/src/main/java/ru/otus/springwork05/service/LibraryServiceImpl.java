package ru.otus.springwork05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springwork05.dao.AuthorDao;
import ru.otus.springwork05.dao.BookDao;
import ru.otus.springwork05.dao.KindBookDao;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final KindBookDao kindDao;

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book getBookByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getAll();
    }

    @Override
    public void addBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.delete(id);
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.insert(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAll();
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public long getEmptyAuthorId() {
        return authorDao.getEmptyId();
    }

    @Override
    public long getEmptyKindId() {
        return kindDao.getEmptyId();
    }

    @Override
    public long getEmptyBookId() {
        return bookDao.getEmptyId();
    }

    @Override
    public void addKindBook(KindBook kind) {
        kindDao.insert(kind);
    }
    @Override
    public List<KindBook> getKindBooks() {
        return kindDao.getAll();
    }

    @Override
    public KindBook getKindBookById(Long id) {
        return kindDao.getById(id);
    }

    @Override
    public void deleteKindBook(long id) {
        kindDao.delete(id);
    }
}
