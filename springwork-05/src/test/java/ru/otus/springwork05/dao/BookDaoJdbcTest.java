package ru.otus.springwork05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, KindBookDaoJdbc.class})
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;
    @Autowired
    private AuthorDaoJdbc authorDao;

    @Autowired
    private KindBookDao kindDao;

    @Test
    void getById() {
        Book book = dao.getById(3);
        assertThat(book.getName()).isEqualTo("Стальная крыса");
    }

    @Test
    void getByName() {
        Book book = dao.getByName("Стальная крыса");
        assertThat(book.getId()).isEqualTo(3);
    }

    @Test
    void insert() {
        var author = new Author(5, "Исаак", "Азимов", "Юдович");
        authorDao.insert(author);
        var kind = kindDao.getByName("Фантастика");
        var book = new Book(4, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)));
        dao.insert(book);
        var addedBook = dao.getByName(book.getName());
        assertThat(addedBook).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void update() {
        var author = new Author(5, "Исаак", "Азимов", "Юдович");
        authorDao.insert(author);
        var kind = kindDao.getByName("Фантастика");
        var book = new Book(3, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)));
        dao.update(book);
        assertThat(dao.getById(3).getName()).isEqualTo("Я, робот");
    }

    @Test
    void delete() {
        var author = new Author(5, "Исаак", "Азимов", "Юдович");
        authorDao.insert(author);
        var kind = kindDao.getByName("Фантастика");
        var book = new Book(4, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)));
        dao.insert(book);
        dao.delete(3);
        assertThat(dao.getAll().size()).isEqualTo(3);
    }

    @Test
    void getEmptyId() {
        var author = new Author(5, "Исаак", "Азимов", "Юдович");
        authorDao.insert(author);
        var kind = kindDao.getByName("Фантастика");
        System.out.println(dao.getAll());
        var book = new Book(10, "Я, робот", new ArrayList<Author>(Arrays.asList(author)), new ArrayList<KindBook>(Arrays.asList(kind)));
        dao.insert(book);
        System.out.println(dao.getAll());
        assertThat(dao.getEmptyId()).isEqualTo(4);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll().size()).isEqualTo(3);
    }
}

