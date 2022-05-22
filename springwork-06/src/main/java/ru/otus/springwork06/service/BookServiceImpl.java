package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.dao.BookDao;
import ru.otus.springwork06.model.Book;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    @Transactional
    public Book merge(Book book) {
        return bookDao.merge(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }
}
