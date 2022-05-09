package ru.otus.springwork06.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.KindBook;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryFacadeImplTest {

    public static final Author author_1 = new Author(1, "authorFirstName", "authorLastName", "authorPatronymic");
    public static final KindBook kindBook_1 = new KindBook(1, "kindBook");
    public static final Book book_1 = new Book(1,
            "bookName",
            new ArrayList<Author>(Arrays.asList(author_1)),
            new ArrayList<KindBook>(Arrays.asList(kindBook_1)),
            new ArrayList<>());

    @Mock
    private BookService bookService;

    @Mock
    private KindBookService kindBookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private CommentaryService commentaryService;

    private LibraryFacade library;

    @BeforeEach
    void setUp() {
        library = new LibraryFacadeImpl(authorService, kindBookService, bookService, commentaryService);
    }


    @Test
    void getBookById() {
        bookService.findById(1);
        verify(bookService, times(1)).findById(1);
    }

    @Test
    void getBooks() {
        library.getBooks();
        verify(bookService, times(1)).findAll();
    }

    @Test
    void addBook() {
        library.saveBook(book_1);
        verify(bookService, times(1)).save(book_1);
    }

    @Test
    void updateBook() {
        library.updateBook(book_1);
        verify(bookService, times(1)).merge(book_1);
    }

    @Test
    void deleteBook() {
        library.saveBook(book_1);
        library.deleteBook(book_1);
        verify(bookService, times(1)).deleteBook(book_1);
    }
}