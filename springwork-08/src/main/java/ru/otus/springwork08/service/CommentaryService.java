package ru.otus.springwork08.service;

import ru.otus.springwork08.model.Commentary;

import java.util.List;

public interface CommentaryService {
    Commentary getById(String id);

    List<Commentary> getAll();

    Commentary save(Commentary commentary);

    Commentary update(Commentary commentary);

    void delete(Commentary commentary);
}
