package ru.otus.springwork06.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, KindBookDaoImpl.class})
class BookDaoImplTest {

    @Autowired
    private BookDao dao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private KindBookDao kindDao;

    @Test
    void findById() {
        Book book = dao.findById(3).get();
        assertThat(book.getName()).isEqualTo("Стальная крыса");
    }

    @Test
    void findAll() {
        List<Book> books = dao.findAll();
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    void save() {
        var author = new Author(5, "Исаак", "Азимов", "Юдович");
        authorDao.save(author);
        var kind = kindDao.findById(3).get();
        var book = new Book(4, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)), null);
        dao.save(book);
        var addedBook = dao.findById(4).get();
        assertThat(addedBook).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void merge() {
        var author = new Author(7, "Исаак", "Азимов", "Юдович");
        authorDao.save(author);
        var kind = kindDao.findById(3).get();
        var book = new Book(3, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)), new ArrayList<>());
        dao.merge(book);
        var mergedBook = dao.findById(3).get();
        assertThat(mergedBook).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void deleteBook() {
        var bookKountBefore = dao.findAll().size();
        var bookFromSource = dao.findById(3).get();
        dao.deleteBook(bookFromSource);
        var bookCount = dao.findAll().size();
        assertThat(bookCount).isLessThan(bookKountBefore);
    }
}