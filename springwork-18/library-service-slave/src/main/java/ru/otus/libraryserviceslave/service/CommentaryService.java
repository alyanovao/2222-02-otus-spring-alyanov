package ru.otus.libraryserviceslave.service;

import ru.otus.libraryserviceslave.model.Commentary;

import java.util.List;

public interface CommentaryService {
    Commentary getById(long id);

    List<Commentary> getAll();

    Commentary save(Commentary commentary);

    Commentary update(Commentary commentary);

    void delete(long id);
}
