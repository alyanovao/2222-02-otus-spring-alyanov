package ru.otus.springwork05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.model.BookView;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import({BookViewDaoImpl.class, BookDaoJdbc.class, AuthorDaoJdbc.class, KindBookDaoJdbc.class})
class BookViewDaoImplTest {

    @Autowired
    private BookViewDaoImpl dao;

    @Test
    void getBookById() {
        BookView book = dao.getBookById(3);
        assertThat(book.getBookName()).isEqualTo("Стальная крыса");
    }

    @Test
    void getBookByName() {
        BookView book = dao.getBookByName("Стальная крыса");
        assertThat(book.getId()).isEqualTo(3);
    }

    @Test
    void getAllBooks() {
        assertThat(dao.getAllBooks().size()).isEqualTo(3);
    }
}