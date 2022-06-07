package ru.otus.springwork09.service;

import ru.otus.springwork09.model.Commentary;

import java.util.List;

public interface CommentaryService {
    Commentary getById(long id);

    List<Commentary> getAll();

    Commentary save(Commentary commentary);

    Commentary update(Commentary commentary);

    void delete(long id);
}
