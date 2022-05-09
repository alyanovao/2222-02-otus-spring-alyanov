package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springwork06.exception.BookNotFoundException;
import ru.otus.springwork06.exception.ComponentException;
import ru.otus.springwork06.exception.RepositoryException;
import ru.otus.springwork06.model.Author;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;
import ru.otus.springwork06.model.KindBook;
import ru.otus.springwork06.processor.IOService;
import ru.otus.springwork06.processor.PrintService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

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
        Author author = new Author(0, firstName, lastName, patronymic);
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
        KindBook kind = new KindBook(0, name);
        library.saveKind(kind);
    }

    @Override
    @ShellMethod(value = "getbook", key = {"getbookbyid", "gi"})
    public void getBookById() {
        var id = ioService.readLongWithPrompt("Введите номер книги:");
        try {
            printService.printBook(library.getBookById(id));
        }
        catch (BookNotFoundException e) {
            ioService.outputMessage("Указанная кника не найдена");
        }
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
            authorList.add(library.getAuthorById(Long.parseLong(author)));
        }

        List<String> kindList = Arrays.asList(kindId.split(","));
        List<KindBook> kindBooks = new ArrayList<>();
        for(String kindBook: kindList) {
            kindBooks.add(library.getKindById(Long.parseLong(kindBook)));
        }
        Book book = new Book(0, name, authorList, kindBooks, null);
        library.saveBook(book);
    }

    @Override
    @ShellMethod(value = "mergebook", key = {"mergebook", "merge"})
    public void mergeBook() {
        var name = ioService.readStringWithPrompt("Введите название книги:");
        var authorId = ioService.readStringWithPrompt("Введите идентификаторы авторов через запятую:");
        var kindId = ioService.readStringWithPrompt("Введите идентификатор жанра:");

        List<String> authors = Arrays.asList(authorId.split(","));
        List<Author> authorList = new ArrayList<>();
        for (String author : authors) {
            authorList.add(library.getAuthorById(Long.parseLong(author)));
        }
        List<String> kindStringList = Arrays.asList(kindId.split(","));
        List<KindBook> kindList = new ArrayList<>();
        for(String kindBook: kindStringList) {
            kindList.add(library.getKindById(Long.parseLong(kindBook)));
        }
        Book book = new Book(0, name, authorList, kindList, null);
        library.updateBook(book);
    }

    @Override
    @ShellMethod(value = "deletebook", key = {"deletebook", "d"})
    public void deleteBook() {
        var id = ioService.readLongWithPrompt("Введите номер книги");
        Book book = library.getBookById(id);
        library.deleteBook(book);
    }

    @Override
    @ShellMethod(value = "getcommentarybyid", key = {"getcommentarybyid", "gc"})
    public void getCommentaryById() {
        var id = ioService.readLongWithPrompt("Введите номер книги:");
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
        var id = ioService.readLongWithPrompt("Введите номер книги:");
        var name = ioService.readStringWithPrompt("Введите текст комментария:");
        try {
            Commentary commentary = new Commentary(0, name, library.getBookById(id).getId());
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
        var id = ioService.readLongWithPrompt("Введите идентификатор комментария:");
        var name = ioService.readStringWithPrompt("Введите текст комментария:");
        Commentary commentary = new Commentary(id, name, library.getCommentaryById(id).getBook_id());
        library.updateCommentary(commentary);
    }

    @Override
    @ShellMethod(value = "deletecommentary", key = {"deletecommentary", "dc"})
    public void deleteCommentary() {
        var id = ioService.readLongWithPrompt("Введите номер комментария:");
        library.deleteCommentary(id);
    }
}
