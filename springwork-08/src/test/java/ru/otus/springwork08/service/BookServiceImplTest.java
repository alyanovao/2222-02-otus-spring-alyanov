package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.springwork08.LibraryTest;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.KindBook;
import ru.otus.springwork08.processor.PrintServiceImpl;
import ru.otus.springwork08.repository.AuthorRepository;
import ru.otus.springwork08.repository.BookRepository;
import ru.otus.springwork08.repository.BookRepositoryCustom;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import({BookServiceImpl.class, PrintServiceImpl.class, AuthorServiceImpl.class, KindBookServiceImpl.class})
class BookServiceImplTest extends LibraryTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KindBookService kindBookService;

    @Test
    void findAll() {
        val books = bookService.findAll();
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    void save() {
        val author2 = new Author("Лермонтов", "Михаил", "Юрьевич");
        authorService.save(author2);
        val kind = new KindBook("kindBook");
        kindBookService.save(kind);
        bookService.save(new Book("Тест", new ArrayList<>(Arrays.asList(author2)), new ArrayList<>(Arrays.asList(kind)), new ArrayList<>()));
        val books = bookService.findAll();
        assertThat(books.size()).isEqualTo(4);
    }

    @Test
    void merge() {
        val book = bookService.findAll().get(0);
        book.setName("Test");
        bookService.merge(book);
        val bookFromService = bookService.findById(book.getId());
        assertThat(book.getName()).isEqualTo(bookFromService.get().getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void deleteBook() {
        val books = bookService.findAll();
        assertThat(books.size()).isEqualTo(3);
        bookService.deleteBook(books.get(0));
        val booksWithoutOneBook = bookService.findAll();
        assertThat(booksWithoutOneBook.size()).isEqualTo(2);
    }

    @Test
    void findBooksByParam() {
        val book = bookService.findAll();
        assertThat(book.size()).isEqualTo(3);
    }
}