package ru.otus.springwork08.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.repository.BookRepository;

import java.util.stream.Collectors;

public class CascadeUpdateAuthorEventListener extends AbstractMongoEventListener<Author> {

    @Autowired
    private BookRepository repository;

    @Override
    public void onAfterSave(AfterSaveEvent<Author> event) {
        Object source = event.getSource();
        if (((Author)source).getId() != null) {
            val authorBook = (Author) source;
            val books = repository.getByAuthorId(authorBook.getId());
            if (!books.isEmpty()) {
                for(Book book:books) {
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
                }
            }
        }
    }
}
