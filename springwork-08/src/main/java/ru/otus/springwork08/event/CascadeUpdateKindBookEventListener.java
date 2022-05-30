package ru.otus.springwork08.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import ru.otus.springwork08.model.Book;
import ru.otus.springwork08.model.KindBook;
import ru.otus.springwork08.repository.BookRepository;

import java.util.stream.Collectors;

public class CascadeUpdateKindBookEventListener extends AbstractMongoEventListener<KindBook> {

    @Autowired
    private BookRepository repository;

    @Override
    public void onAfterSave(AfterSaveEvent<KindBook> event) {
        Object source = event.getSource();

        if (((KindBook)source).getId() != null) {
            val kindBook = (KindBook) source;
            val books = repository.getByKindBookId(kindBook.getId());
            if (!books.isEmpty()) {
                for (Book book : books) {
                    val kinds = book.getKind();
                    kinds.stream()
                            .filter(kind -> kind.getId().equals(kindBook.getId()))
                            .map(kind -> {
                                kind.setName(kindBook.getName());
                                return kind;
                            })
                            .collect(Collectors.toList());
                    book.setKind(kinds);
                    repository.save(book);
                }
            }
        }
    }
}
