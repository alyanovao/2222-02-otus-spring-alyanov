package ru.otus.springwork07.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springwork07.model.Author;
import ru.otus.springwork07.model.Book;
import ru.otus.springwork07.model.Commentary;
import ru.otus.springwork07.model.KindBook;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    private final IOService service;

    @Override
    public void printBook(Book book) {
        print(book);
    }

    private void print(Book book) {
        service.outputMessage("Идентификатор: " + book.getId());
        service.outputMessage("Название:" + book.getName());
        service.outputMessage("Автор:" + book.getAuthors().stream().map(author -> {
            return author.getFirstName() + " " + author.getLastName() + " " + author.getPatronymic();
        }).collect(Collectors.joining(", ")));
        service.outputMessage("Жанр:" + book.getKind().stream().map(kindBook -> {
            return kindBook.getName();
        }).collect(Collectors.joining(", ")));
        service.outputMessage("Комментарии:" + book.getCommentary().stream().map(commentary -> {
            return commentary.getValue();
        }).collect(Collectors.joining(", ")));
        service.outputMessage("");
    }

    @Override
    public void printBook(List<Book> book) {
        for(Book element: book) {
            print(element);
        }
    }

    @Override
    public void printAuthor(Author author) {
        print(author);
    }

    private void print(Author author) {
        service.outputMessage("Идентификатор: " + author.getId());
        service.outputMessage("Фамилия: " + author.getFirstName());
        service.outputMessage("Имя: " + author.getLastName());
        service.outputMessage("Отчество: " + author.getPatronymic());
        service.outputMessage("");
    }

    @Override
    public void printAuthor(List<Author> authors) {
        for (Author author: authors) {
            print(author);
        }
    }

    @Override
    public void printKindBook(List<KindBook> kinds) {
        for(KindBook kindBook: kinds) {
            service.outputMessage("Идентификатор: " + kindBook.getId());
            service.outputMessage("Название: " + kindBook.getName());
            service.outputMessage("");
        }
    }

    @Override
    public void printCommentaries(List<Commentary> commentaries) {
        for(Commentary commentary: commentaries) {
            service.outputMessage("Идентификатор комментария:" + commentary.getId());
            service.outputMessage("Текст комментария: " + commentary.getValue());
            service.outputMessage("");
        }
    }
}
