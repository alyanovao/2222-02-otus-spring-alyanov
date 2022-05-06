package ru.otus.springwork06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork06.model.Author;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами")
@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private EntityManager em;

    @Test
    void findById() {
        var author = new Author(0, "Test", "Test", "Testovich");
        authorDao.save(author);
        Author authorAfterInsert = authorDao.findById(author.getId()).get();
        assertThat(authorAfterInsert).usingRecursiveComparison().isEqualTo(author);
    }

    @Test
    void findAll() {
        authorDao.save(new Author(0, "Test", "Test", "Testovich"));
        var authors = authorDao.findAll();
        assertThat(authors.size()).isEqualTo(5);
    }

    @Test
    void save() {
        var author = new Author(0, "Test", "Test", "Testovich");
        authorDao.save(author);
        Author authorAfterInsert = authorDao.findById(author.getId()).get();
        assertThat(authorAfterInsert).usingRecursiveComparison().isEqualTo(author);
    }
}