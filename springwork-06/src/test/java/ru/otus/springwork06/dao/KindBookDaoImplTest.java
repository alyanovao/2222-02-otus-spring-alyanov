package ru.otus.springwork06.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork06.model.KindBook;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest()
@Import(KindBookDaoImpl.class)
class KindBookDaoImplTest {

    @Autowired
    private KindBookDao dao;

    @Test
    void findById() {
        var kind = dao.findById(1);
        assertThat(kind.get()).isNotNull();
    }

    @Test
    void findAll() {
        var kinds = dao.findAll();
        assertThat(kinds.size()).isEqualTo(3);
    }

    @Test
    void save() {
        dao.save(new KindBook(0, "Test"));
        assertThat(dao.findAll().size()).isGreaterThan(3);

    }
}