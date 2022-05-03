package ru.otus.springwork05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.model.KindBook;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(KindBookDaoJdbc.class)
class KindBookDaoJdbcTest {

    @Autowired
    private KindBookDaoJdbc dao;

    @Test
    @DisplayName(value = "Проверить поиск по идентификатору на основании миграционных скриптов")
    void getById() {
        var kind = dao.getById(3);
        assertThat(kind.getName()).isEqualTo("Фантастика");
    }

    @Test
    void getByName() {
        var kind = dao.getByName("Фантастика");
        assertThat(kind.getId()).isEqualTo(3);
    }

    @Test
    void getEmptyId() {
        dao.insert(new KindBook(5, "Проза"));
        assertThat(dao.getEmptyId()).isEqualTo(4);
    }

    @Test
    void insert() {
        assertThat(dao.getAll().size()).isEqualTo(3);
        dao.insert(new KindBook(5, "Проза"));
        assertThat(dao.getAll().size()).isEqualTo(4);
    }

    @Test
    void delete() {
        assertThat(dao.getAll().size()).isEqualTo(3);
        dao.insert(new KindBook(5, "Проза"));
        assertThat(dao.getAll().size()).isEqualTo(4);
        dao.delete(5);
        assertThat(dao.getAll().size()).isEqualTo(3);
    }

    @Test
    void getAll() {
        assertThat(dao.getAll().size()).isEqualTo(3);
    }
}