package ru.otus.springwork11.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import ru.otus.springwork11.model.Author;
import ru.otus.springwork11.repository.BookRepository;

import java.util.stream.Collectors;

public class CascadeUpdateAuthorEventListener extends AbstractMongoEventListener<Author> {

    @Autowired
    private BookRepository repository;

    @Override
    public void onAfterSave(AfterSaveEvent<Author> event) {
        Object source = event.getSource();
        if (((Author)source).getId() != null) {
            val authorBook = (Author) source;
            repository.findById(authorBook.getId())
                    .map(book -> {
                        val authors = book.getAuthors();
                        authors.stream()
                                .filter(author -> author.getId().equals(authorBook.getId()))
                                .map(author -> {
                                    author.setFirstName(authorBook.getFirstName());
                                    author.setLastName(authorBook.getLastName());
                                    author.setPatronymic(authorBook.getPatronymic());
                                    return author;
                                })
                                .collect(Collectors.toList());
                        book.setAuthors(authors);
                        repository.save(book);
                        return book;
                    })
                    .subscribe();
        }
    }
}
