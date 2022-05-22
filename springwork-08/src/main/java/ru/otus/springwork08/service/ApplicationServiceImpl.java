package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springwork08.exception.*;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.model.KindBook;
import ru.otus.springwork08.processor.IOService;
import ru.otus.springwork08.processor.PrintService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    public static final String WRITE_BOOK_NUMBER = "Введите номер книги:";
    private final LibraryFacade library;
    private final PrintService printService;
    private final IOService ioService;

    @Override
    @ShellMethod(value = "getauthors", key = {"getauthors", "ga"})
    public void getAuthors() {
        printService.printAuthor(library.getAuthors());
    }

    @Override
    @ShellMethod(value = "saveauthor", key = {"saveaithor", "sa"})
    public void saveAuthor() {
        var firstName = ioService.readStringWithPrompt("Введите фамилию автора:");
        var lastName = ioService.readStringWithPrompt("Введите имя автора:");
        var patronymic = ioService.readStringWithPrompt("Введите отчество автора:");
        Author author = new Author(firstName, lastName, patronymic);
        library.saveAuthor(author);
    }

    @Override
    @ShellMethod(value = "getkinds", key = {"getkinds", "gk"})
    public void getKindBooks() {
        printService.printKindBook(library.getKinds());
    }

    @Override
    @ShellMethod(value = "savekind", key = {"savekind", "sk"})
    public void saveKindBook() {
        var name = ioService.readStringWithPrompt("Введите название жанра:");
        KindBook kind = new KindBook(name);
        library.saveKind(kind);
    }

    @Override
    @ShellMethod(value = "getbook", key = {"getbookbyid", "gi"})
    public void getBookById() {
        var id = ioService.readStringWithPrompt(WRITE_BOOK_NUMBER);
        try {
            printService.printBook(library.getBookById(id));
        }
        catch (BookNotFoundException e) {
            ioService.outputMessage("Указанная книга не найдена");
        }
    }

    @ShellMethod(value = "getbooksbyparams", key = {"getbooksbyparams", "bs"})
    public void getBooksByParams() {
        var firstName = ioService.readStringWithPrompt("Введите фио автора:");
        var kind = ioService.readStringWithPrompt("Введите жанр:");
        List<Book> books = library.findBooksByParam(firstName, kind);
        printService.printBook(books);
    }

    @Override
    @ShellMethod(value = "getbooks", key = {"getbooks", "b"})
    public void getBooks() {
        printService.printBook(library.getBooks());
    }

    @Override
    @ShellMethod(value = "savebook", key = {"savebook", "s"})
    public void saveBook() {
        var name = ioService.readStringWithPrompt("Введите название книги:");
        var authorId = ioService.readStringWithPrompt("Введите идентификаторы авторов через запятую:");
        var kindId = ioService.readStringWithPrompt("Введите идентификатор жанра:");

        List<String> authors = Arrays.asList(authorId.split(","));
        List<Author> authorList = new ArrayList<>();

        for (String author : authors) {
            try {
                authorList.add(library.getAuthorById(author));
            }
            catch (AuthorNotFoundException e) {
                ioService.outputMessage("По указанным параметрам автор не найден");
                return;
            }
        }

        List<String> kindList = Arrays.asList(kindId.split(","));
        List<KindBook> kindBooks = new ArrayList<>();
        for(String kindBook: kindList) {
            try {
                kindBooks.add(library.getKindById(kindBook));
            }
            catch (KindBookNotFoundException e) {
                ioService.outputMessage("По укзанным параметрам жанр не найден");
                return;
            }
        }
        Book book = new Book(name, authorList, kindBooks, new ArrayList<>());
        library.saveBook(book);
    }

    @Override
    @ShellMethod(value = "mergebook", key = {"mergebook", "merge"})
    public void mergeBook() {
        val id = ioService.readStringWithPrompt("Введите ид книги:");
        val name = ioService.readStringWithPrompt("Введите название книги:");
        val authorId = ioService.readStringWithPrompt("Введите идентификаторы авторов через запятую:");
        val kindId = ioService.readStringWithPrompt("Введите идентификатор жанра:");

        val book = library.getBookById(id);
        List<String> authors = Arrays.asList(authorId.split(","));
        List<Author> authorList = new ArrayList<>();
        for (String author : authors) {
            authorList.add(library.getAuthorById(author));
        }
        List<String> kindStringList = Arrays.asList(kindId.split(","));
        List<KindBook> kindList = new ArrayList<>();
        for(String kindBook: kindStringList) {
            kindList.add(library.getKindById(kindBook));
        }
        book.setName(name);
        book.setAuthors(authorList);
        book.setKind(kindList);
        library.updateBook(book);
    }

    @Override
    @ShellMethod(value = "deletebook", key = {"deletebook", "d"})
    public void deleteBook() {
        var id = ioService.readStringWithPrompt(WRITE_BOOK_NUMBER);
        Book book = library.getBookById(id);
        library.deleteBook(book);
    }

    @Override
    @ShellMethod(value = "getcommentarybyid", key = {"getcommentarybyid", "gc"})
    public void getCommentaryById() {
        var id = ioService.readStringWithPrompt(WRITE_BOOK_NUMBER);
        List<Commentary> commentary = library.getBookCommentaries(id);
        printService.printCommentaries(commentary);
    }

    @Override
    @ShellMethod(value = "getsllcommentaries", key = {"getallcommentaries", "gcs"})
    public void getAllCommentaries() {
        printService.printCommentaries(library.getAllCommentaries());
    }

    @Override
    @ShellMethod(value = "savecommentary", key = {"savecommentary", "sc"})
    public void saveCommentary() {
        var id = ioService.readStringWithPrompt(WRITE_BOOK_NUMBER);
        var name = ioService.readStringWithPrompt("Введите текст комментария:");
        try {
            Book book = library.getBookById(id);
            Commentary commentary = new Commentary(name, book.getId());
            library.saveCommentary(commentary);
        }
        catch(ComponentException e) {
            ioService.outputMessage("Не удалось сохранить комментарий");
        }
        catch(RepositoryException e) {
            ioService.outputMessage("Не удалось сохранить комент");
        }
    }

    @Override
    @ShellMethod(value = "updatecommentary", key = {"updatecommentary", "uc"})
    public void updateCommentary() {
        var id = ioService.readStringWithPrompt("Введите идентификатор комментария:");
        var name = ioService.readStringWithPrompt("Введите текст комментария:");
        Commentary commentary = library.getCommentaryById(id);
        commentary.setValue(name);
        library.updateCommentary(commentary);
    }

    @Override
    @ShellMethod(value = "deletecommentary", key = {"deletecommentary", "dc"})
    public void deleteCommentary() {
        var id = ioService.readStringWithPrompt("Введите номер комментария:");
        Commentary commentary = library.getCommentaryById(id);
        library.deleteCommentary(commentary);
    }
}
