package ru.otus.springwork06.service;

import ru.otus.springwork06.model.Commentary;

import java.util.List;

public interface CommentaryService {
    Commentary getById(long id);

    List<Commentary> getAll();

    Commentary save(Commentary commentary);

    Commentary update(Commentary commentary);

    void delete(long id);
}
