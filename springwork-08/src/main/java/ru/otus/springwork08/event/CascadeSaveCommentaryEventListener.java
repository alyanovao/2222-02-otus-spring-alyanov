package ru.otus.springwork08.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.repository.BookRepository;
import ru.otus.springwork08.repository.CommentaryRepository;

import java.util.ArrayList;

public class CascadeSaveCommentaryEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Commentary) && (((Commentary) source).getBookId() != null)) {

            val commentaryBook = (Commentary) source;
            val book = repository.getById(commentaryBook.getBookId());

            val commentaries = book.getCommentary();

            if (commentaries.stream()
                    .filter(commentary -> commentaryBook.getId()
                            .equals(commentary.getId()))
                            .findFirst()
                    .orElse(null) == null) {
                commentaries.add(commentaryBook);
                book.setCommentary(commentaries);
                repository.save(book);
            }
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        Object source = event.getSource();
        if (source instanceof Commentary) {

            Commentary commentary = (Commentary) source;
            Book book = repository.getById(commentary.getBookId());
            book.setCommentary(new ArrayList<>());
            repository.save(book);
        }
    }
}
