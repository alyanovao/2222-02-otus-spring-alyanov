package ru.otus.springwork11.data;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.jetbrains.annotations.NotNull;
import ru.otus.springwork11.model.Author;
import ru.otus.springwork11.model.Book;
import ru.otus.springwork11.model.KindBook;
import ru.otus.springwork11.repository.AuthorRepository;
import ru.otus.springwork11.repository.BookRepository;
import ru.otus.springwork11.repository.KindBookRepository;

import java.util.ArrayList;
import java.util.Arrays;

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
        repository.save(author1).subscribe();
        repository.save(author2).subscribe();
        repository.save(author3).subscribe();
    }

    @ChangeSet(order = "003", id = "saveKind", author = "alyanovao")
    public void saveKindBook(@NotNull KindBookRepository repository) {
        repository.save(kind1).subscribe();
        repository.save(kind2).subscribe();
        repository.save(kind3).subscribe();
    }

    @ChangeSet(order = "004", id = "saveBook", author = "alyanovao")
    public void saveKindBook(@NotNull BookRepository repository) {
        repository.save(new Book("Сказки Пушкина", new ArrayList<>(Arrays.asList(author1))
                , new ArrayList<>(Arrays.asList(kind1))
                , new ArrayList<>())).subscribe();

        repository.save(new Book("Неукротимая планета", new ArrayList<>(Arrays.asList(author3))
                , new ArrayList<>(Arrays.asList(kind3))
                , new ArrayList<>())).subscribe();

        repository.save(new Book("Стальная крыса", new ArrayList<>(Arrays.asList(author3))
                , new ArrayList<>(Arrays.asList(kind3))
                , new ArrayList<>())).subscribe();
    }
}
