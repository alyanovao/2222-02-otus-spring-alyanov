package ru.otus.springwork06.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork06.dao.AuthorDao;
import ru.otus.springwork06.dao.BookDao;
import ru.otus.springwork06.dao.CommentaryDao;
import ru.otus.springwork06.dao.KindBookDao;
import ru.otus.springwork06.exception.BookNotFoundException;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

    public static final Author author_1 = new Author(1, "authorFirstName", "authorLastName", "authorPatronymic");
    public static final KindBook kindBook_1 = new KindBook(1, "kindBook");
    public static final Book book_1 = new Book(1,
            "bookName",
            new ArrayList<Author>(Arrays.asList(author_1)),
            new ArrayList<KindBook>(Arrays.asList(kindBook_1)),
            new ArrayList<>());

    @Mock
    private BookDao bookDao;

    @Mock
    private KindBookDao kindBookDao;

    @Mock
    private AuthorDao authorDao;

    @Mock
    private CommentaryDao commentaryDao;

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryServiceImpl(authorDao, kindBookDao, bookDao, commentaryDao);
    }


    @Test
    void getBookById() {
        bookDao.findById(1);
        verify(bookDao, times(1)).findById(1);
    }

    @Test
    void getBooks() {
        service.getBooks();
        verify(bookDao, times(1)).findAll();
    }

    @Test
    void addBook() {
        service.saveBook(book_1);
        verify(bookDao, times(1)).save(book_1);
    }

    @Test
    void updateBook() {
        service.updateBook(book_1);
        verify(bookDao, times(1)).merge(book_1);
    }

    @Test
    void deleteBook() {
        service.deleteById(1);
        verify(bookDao, times(1)).deleteById(1);
    }
}