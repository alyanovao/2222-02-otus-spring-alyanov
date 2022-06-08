package ru.otus.springwork09.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.springwork09.model.Author;
import ru.otus.springwork09.model.Book;
import ru.otus.springwork09.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    public static final Author author_1 = new Author(1, "authorFirstName", "authorLastName", "authorPatronymic");
    public static final KindBook kindBook_1 = new KindBook(1, "kindBook");
    public static final Book book_1 = new Book(1,
            "bookName",
            new ArrayList<Author>(Arrays.asList(author_1)),
            new ArrayList<KindBook>(Arrays.asList(kindBook_1)),
            new ArrayList<>());

    @Mock
    private BookService bookService;

    @Test
    void findById() {
        bookService.findById(1);
        verify(bookService, times(1)).findById(1);
    }

    @Test
    void findBooksByParam() {
        bookService.findBooksByParam("", "");
        verify(bookService, times(1)).findBooksByParam("", "");
    }

    @Test
    void findAll() {
        bookService.findAll();
        verify(bookService, times(1)).findAll();
    }

    @Test
    void save() {
        bookService.save(book_1);
        verify(bookService, times(1)).save(book_1);
    }

    @Test
    void merge() {
        bookService.merge(book_1);
        verify(bookService, times(1)).merge(book_1);
    }

    @Test
    void deleteBook() {
        bookService.save(book_1);
        bookService.deleteBook(book_1);
        verify(bookService, times(1)).deleteBook(book_1);

    }
}