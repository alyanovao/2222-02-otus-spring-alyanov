package ru.otus.springwork05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с авторами")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Test
    @DisplayName("возвращать пропущенный идентификатор автора в БД")
    void getEmptyId() {
        Book book = new Book(5, "Test", 3, 3);
        long emptyCountId = authorDao.getEmptyId();
        assertThat(emptyCountId).isEqualTo(4);
    }

    @Test
    @DisplayName("проверить размер после добавления автора в БД")
    void insert() {
        var authors = authorDao.getAll();
        assertThat(authors.size()).isEqualTo(3);
        Author author = new Author(4, "Test", "Test", "Testovich");
        authorDao.insert(author);
        var authorsAfterAdd = authorDao.getAll();
        assertThat(authorsAfterAdd.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверить список всех авторов")
    void getAll() {
        var authorsAfterAdd = authorDao.getAll();
        assertThat(authorsAfterAdd.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Получить автора по идентификатору")
    void getById() {
        var author = new Author(4, "Test", "Test", "Testovich");
        authorDao.insert(author);
        Author authorAfterInsert = authorDao.getById(4);
        assertThat(authorAfterInsert).usingRecursiveComparison().isEqualTo(author);
    }
}