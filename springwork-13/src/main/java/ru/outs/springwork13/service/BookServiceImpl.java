package ru.outs.springwork13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.outs.springwork13.model.Book;
import ru.outs.springwork13.repository.BookDao;

import java.util.List;
import java.util.Optional;

import static ru.outs.springwork13.repository.BookSpecification.firstNameLike;
import static ru.outs.springwork13.repository.BookSpecification.kindLike;

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
        return bookDao.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public List<Book> findBooksByParam(String firstName, String kindName) {
        Specification<Book> specification = Specification.where(firstNameLike(firstName)).or(kindLike(kindName));
        List<Book> books = bookDao.findAll(specification);
        return books;
    }
}
