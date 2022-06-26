package ru.otus.springwork11.data;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import ru.otus.springwork11.model.Author;
import ru.otus.springwork11.model.Book;
import ru.otus.springwork11.model.KindBook;
import ru.otus.springwork11.repository.AuthorRepository;
import ru.otus.springwork11.repository.BookRepository;
import ru.otus.springwork11.repository.KindBookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog
public class Changelog {


    private Author author1 = new Author("Пушкин", "Александр", "Сергеевич");
    private Author author2 = new Author("Лермонтов", "Михаил", "Юрьевич");
    private Author author3 = new Author("Генри", "Максвел", "Демпси");

    private KindBook kind1 = new KindBook("Сказки");
    private KindBook kind2 = new KindBook("Стихи");
    private KindBook kind3 = new KindBook("Фантастика");

    @ChangeSet(order = "001", id = "dropDB", author = "alyanovao", runAlways = true)
    public void dropDb(@NotNull MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "saveAuthor", author = "alyanovao")
    public void saveAuthor(@NotNull AuthorRepository repository) {
        val author = List.of(author1, author2, author3);
        repository.saveAll(author).subscribe();
    }

    @ChangeSet(order = "003", id = "saveKind", author = "alyanovao")
    public void saveKindBook(@NotNull KindBookRepository repository) throws InterruptedException {
        val kinds = List.of(kind1, kind2, kind3);
        repository.saveAll(kinds).subscribe();

        Thread.sleep(1_000);
    }

    @ChangeSet(order = "004", id = "saveBook", author = "alyanovao")
    public void saveKindBook(@NotNull BookRepository repository) {
        val books = List.of(
                new Book("Сказки Пушкина", new ArrayList<>(Arrays.asList(author1))
                    , new ArrayList<>(Arrays.asList(kind1))
                    , new ArrayList<>()),
                new Book("Неукротимая планета", new ArrayList<>(Arrays.asList(author3))
                    , new ArrayList<>(Arrays.asList(kind3))
                    , new ArrayList<>()),
                new Book("Стальная крыса", new ArrayList<>(Arrays.asList(author3))
                    , new ArrayList<>(Arrays.asList(kind3))
                    , new ArrayList<>()));
        repository.saveAll(books).subscribe();

    }
}
