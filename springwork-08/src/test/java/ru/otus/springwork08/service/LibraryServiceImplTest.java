package ru.otus.springwork08.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

    public static final Author author_1 = new Author("authorFirstName", "authorLastName", "authorPatronymic");
    public static final KindBook kindBook_1 = new KindBook("kindBook");
    public static final Book book_1 = new Book(
            "bookName",
            new ArrayList<Author>(Arrays.asList(author_1)),
            new ArrayList<KindBook>(Arrays.asList(kindBook_1)),
            new ArrayList<>());

    @Mock
    private BookService bookService;

    @Mock
    private KindBookService kindBookService;

    @Mock
    private CommentaryService commentaryService;

    @Mock
    private AuthorService authorService;

    private LibraryFacade libraryFacade;

    @BeforeEach
    void setUp() {
        libraryFacade = new LibraryFacadeImpl(authorService, kindBookService, bookService, commentaryService);
    }


    @Test
    void getBookById() {
        var book = bookService.findById(book_1.getId());
        verify(bookService, times(1)).findById(book_1.getId());
        assertThatCode(() -> bookService.save(book_1)).doesNotThrowAnyException();
    }

    @Test
    void getBooks() {
        libraryFacade.getBooks();
        verify(bookService, times(1)).findAll();
    }

    @Test
    void addBook() {
        libraryFacade.saveBook(book_1);
        verify(bookService, times(1)).save(book_1);
    }

    @Test
    void updateBook() {
        libraryFacade.updateBook(book_1);
        verify(bookService, times(1)).merge(book_1);
    }

    @Test
    void deleteBook() {
        libraryFacade.deleteBook(book_1);
        verify(bookService, times(1)).deleteBook(book_1);
    }
}