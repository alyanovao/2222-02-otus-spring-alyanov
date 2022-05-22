package ru.otus.springwork06.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork06.model.Commentary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(CommentaryDaoImpl.class)
class CommentaryDaoImplTest {

    @Autowired
    private CommentaryDao commentaryDao;

    @Test
    void getById() {
        var commentary = commentaryDao.getById(1);
        assertThat(commentary).isNotNull();
    }

    @Test
    void getAllByBookId() {
        var comments = commentaryDao.getById(2);
        assertThat(comments.getId()).isEqualTo(2);
    }

    @Test
    void save() {
        commentaryDao.save(new Commentary(0, "Test", 3));
        assertThat(commentaryDao.getById(3).getId()).isEqualTo(3);
    }

    @Test
    void update() {
        commentaryDao.update(new Commentary(2, "Test", 3));
        assertThat(commentaryDao.getById(2)).usingRecursiveComparison().isEqualTo(new Commentary(2, "Test", 3));
    }

    @Test
    void delete() {
        var commentaryCount = commentaryDao.getAll();
        var commentary = commentaryDao.getById(1);
        commentaryDao.delete(commentary);
        var commentaryAfterDelete = commentaryDao.getAll();
        assertThat(commentaryCount.size()).isEqualTo(commentaryAfterDelete.size());
    }
}