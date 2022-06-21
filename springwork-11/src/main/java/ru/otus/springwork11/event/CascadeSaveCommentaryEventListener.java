package ru.otus.springwork11.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import ru.otus.springwork11.model.Commentary;
import ru.otus.springwork11.repository.BookRepository;

import java.util.ArrayList;

public class CascadeSaveCommentaryEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private BookRepository repository;

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Commentary) && (((Commentary) source).getBookId() != null)) {

            val commentaryBook = (Commentary) source;
            repository.findById(commentaryBook.getBookId())
                    .map(book1 -> {
                        val commentaries = book1.getCommentary();
                        if (commentaries.stream()
                                .filter(commentary -> commentaryBook.getId()
                                        .equals(commentary.getId()))
                                .findFirst()
                                .orElse(null) == null) {
                            commentaries.add(commentaryBook);
                            book1.setCommentary(commentaries);
                            repository.save(book1);
                        }
                        return book1;
                    })
                    .subscribe();
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        Object source = event.getSource();
        if (source instanceof Commentary) {

            Commentary commentary = (Commentary) source;
            repository.findById(commentary.getId())
                    .map(book -> {
                        book.setCommentary(new ArrayList<>());
                        repository.save(book);
                        return book;
                    })
                    .subscribe();
        }
    }
}
