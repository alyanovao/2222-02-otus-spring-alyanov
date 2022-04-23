package ru.otus.springwork05.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.springwork05.dao.*;
import ru.otus.springwork05.model.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({LibraryServiceImpl.class, BookDaoJdbc.class, BookViewDaoImpl.class, KindBookDaoJdbc.class, AuthorDaoJdbc.class})
class LibraryServiceImplTest {

    @Autowired
    private LibraryServiceImpl service;

    @Test
    void getBookById() {
        var book = service.getBookById(3);
        assertThat(book.getBookName()).isEqualTo("Стальная крыса");
    }

    @Test
    void getBooks() {
        assertThat(service.getBooks().size()).isEqualTo(3);
    }

    @Test
    void addBook() {
        Book book = new Book(4, "Test", 3, 3);
        service.addBook(book);
        assertThat(service.getBookById(4).getBookName()).isEqualTo(book.getName());
    }

    @Test
    void updateBook() {
        Book book = new Book(3, "Test", 3, 3);
        service.updateBook(book);
        assertThat(service.getBookById(3).getBookName()).isEqualTo(book.getName());
    }

    @Test
    void deleteBook() {
        Book book = new Book(4, "Книга", 3, 3);
        service.addBook(book);
        service.deleteBook(3);
        assertThat(service.getBooks().size()).isEqualTo(3);
        assertThat(service.getBookById(4).getBookName()).isEqualTo(book.getName());
    }
}