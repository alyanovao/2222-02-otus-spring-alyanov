package ru.otus.springwork05.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork05.dao.AuthorDao;
import ru.otus.springwork05.dao.BookDao;
import ru.otus.springwork05.dao.KindBookDao;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

    public static final Author author_1 = new Author(1, "authorFirstName", "authorLastName", "authorPatronymic");
    public static final KindBook kindBook_1 = new KindBook(1, "kindBook");
    public static final Book book_1 = new Book(1,
            "bookName",
            new ArrayList<Author>(Arrays.asList(author_1)),
            new ArrayList<KindBook>(Arrays.asList(kindBook_1)));

    @Mock
    private BookDao bookDao;

    @Mock
    private KindBookDao kindBookDao;

    @Mock
    private AuthorDao authorDao;

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryServiceImpl(bookDao, authorDao, kindBookDao);
    }


    @Test
    void getBookById() {
        var book = service.getBookById(1);
        verify(bookDao, times(1)).getById(1);
        assertThatCode(() -> service.addBook(book_1)).doesNotThrowAnyException();
    }

    @Test
    void getBooks() {
        service.getBooks();
        verify(bookDao, times(1)).getAll();
    }

    @Test
    void addBook() {
        service.addBook(book_1);
        verify(bookDao, times(1)).insert(book_1);
    }

    @Test
    void updateBook() {
        service.updateBook(book_1);
        verify(bookDao, times(1)).update(book_1);
    }

    @Test
    void deleteBook() {
        service.deleteBook(1);
        verify(bookDao, times(1)).delete(1);
    }
}