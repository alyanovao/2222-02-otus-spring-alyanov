package ru.otus.springwork05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class})
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;
    @Autowired
    private AuthorDaoJdbc authorDao;

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
        var author = new Author(4, "Исаак", "Азимов", "Юдович");
        authorDao.insert(author);
        var book = new Book(4, "Я, робот", 4, 3);
        dao.insert(book);
        var addedBook = dao.getByName(book.getName());
        assertThat(addedBook).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void update() {
        authorDao.insert(new Author(4, "Исаак", "Азимов", "Юдович"));
        dao.update(new Book(3, "Я, робот", 4, 3));
        assertThat(dao.getById(3).getName()).isEqualTo("Я, робот");
    }

    @Test
    void delete() {
        authorDao.insert(new Author(4, "Исаак", "Азимов", "Юдович"));
        dao.insert(new Book(4, "Я, робот", 4, 3));
        dao.delete(3);
        assertThat(dao.getAll().size()).isEqualTo(3);
    }

    @Test
    void getEmptyId() {
        authorDao.insert(new Author(4, "Исаак", "Азимов", "Юдович"));
        dao.insert(new Book(5, "Я, робот", 4, 3));
        assertThat(dao.getEmptyId()).isEqualTo(4);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll().size()).isEqualTo(3);
    }
}
