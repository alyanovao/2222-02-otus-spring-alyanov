package ru.otus.springwork05.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.BookView;
import ru.otus.springwork05.model.KindBook;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    private final IOService service;

    @Override
    public void printBookView(BookView bookView) {
        print(bookView);
    }

    private void print(BookView bookView) {
        service.outputMessage("Идентификатор: " + bookView.getId());
        service.outputMessage("Название:" + bookView.getBookName());
        service.outputMessage("Автор:" + bookView.getAuthorName());
        service.outputMessage("Жанр:" + bookView.getKindBook());
        service.outputMessage("");
    }

    @Override
    public void printBookView(List<BookView> bookView) {
        for(BookView book: bookView) {
            print(book);
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
        service.outputMessage("Отчество: " + author.getPartonymic());
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
}
