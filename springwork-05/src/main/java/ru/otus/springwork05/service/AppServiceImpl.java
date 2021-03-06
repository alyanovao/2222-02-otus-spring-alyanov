package ru.otus.springwork05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springwork05.exception.BookNotFoundException;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;
import ru.otus.springwork05.processor.IOService;
import ru.otus.springwork05.processor.PrintService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class AppServiceImpl implements AppService {

    private final LibraryService library;
    private final IOService ioService;
    private final PrintService printService;

    @Override
    @ShellMethod(value = "getBookById", key = {"id", "i"})
    public void getBookById() {
        long id = ioService.readLongWithPrompt("Введите id книги:");
        Book book;
        try {
            book = library.getBookById(id);
            printService.printBook(book);
        }
        catch (BookNotFoundException e) {
            ioService.outputMessage("Указанная книга не найдена");
        }
    }

    @Override
    @ShellMethod(value = "getBookByName", key = {"name", "n"})
    public void getBookByName() {
        String name = ioService.readStringWithPrompt("Введите название книги:");

        try {
            Book book = library.getBookByName(name);
            printService.printBook(book);
        }
        catch (BookNotFoundException e) {
            ioService.outputMessage("Указанная книга не найдена");
        }
    }

    @Override
    @ShellMethod(value = "getBooks", key = {"books", "b"})
    public void getBooks() {
        var books = library.getBooks();
        printService.printBook(books);
    }

    @Override
    @ShellMethod(value = "addBook", key = {"addbook", "ab"})
    public void addBook() {
        Book book = getNewBook(library.getEmptyBookId());
        library.addBook(book);
    }

    private Book getNewBook(long id) {
        var name = ioService.readStringWithPrompt("Введите название книги:");
        var authorId = ioService.readStringWithPrompt("Введите идентификаторы авторов через запятую:");
        var kindId = ioService.readLongWithPrompt("Введите идентификатор жанра:");
        List<Author> authors = new ArrayList<>();
        List<String> authorList = Arrays.asList(authorId.split(","));
        for (int i = 0; i <= authorList.size() - 1; i++) {
            Author author = library.getAuthorById(Long.parseLong(authorList.get(i)));
            authors.add(author);
        }
        List<KindBook> kind = new ArrayList<>();
        kind.add(library.getKindBookById(kindId));
        return new Book(id, name, authors, kind);
    }

    @Override
    @ShellMethod(value = "updateBook", key = {"updateBook", "u"})
    public void updateBook() {
        var bookId = ioService.readLongWithPrompt("Введите идентификатор книги:");
        Book book = getNewBook(bookId);
        library.updateBook(book);
    }

    @Override
    @ShellMethod(value = "deleteBook", key = {"deleteBook", "d"})
    public void deleteBook() {
        var id = ioService.readLongWithPrompt("Введите идентификатор книги:");
        library.deleteBook(id);
    }

    @Override
    @ShellMethod(value = "addAuthor", key = {"addauthor", "au"})
    public void addAuthor() {
        var firstName = ioService.readStringWithPrompt("Введите фамилию автора:");
        var lastName = ioService.readStringWithPrompt("Введите имя автора:");
        var patronimyc = ioService.readStringWithPrompt("Введите отчество автора:");
        Author author = new Author(library.getEmptyAuthorId(), firstName, lastName, patronimyc);
        library.addAuthor(author);
    }

    @Override
    @ShellMethod(value = "getAuthors", key = {"getauthors", "ga"})
    public void getAuthors() {
        List<Author> authors = library.getAuthors();
        printService.printAuthor(authors);
    }

    @Override
    @ShellMethod(value = "addKindBook", key = {"addkindbook", "ak"})
    public void addKindBook() {
        String name = ioService.readStringWithPrompt("Введите название жанра: ");
        library.addKindBook(new KindBook(library.getEmptyKindId(), name));
    }

    @Override
    @ShellMethod(value = "getKindBooks", key = {"getkindbooks", "gk"})
    public void getKindBooks() {
        List<KindBook> kinds = library.getKindBooks();
        printService.printKindBook(kinds);
    }

    @Override
    @ShellMethod(value = "deleteKindBook", key = {"deletekindbook", "dk"})
    public void deleteKindBook() {
        var id = ioService.readLongWithPrompt("Введите идентификатор жанра:");
        library.deleteKindBook(id);
    }
}
