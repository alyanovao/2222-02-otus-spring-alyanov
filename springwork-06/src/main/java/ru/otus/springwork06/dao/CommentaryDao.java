package ru.otus.springwork06.dao;

import ru.otus.springwork06.model.Commentary;

import java.util.List;

public interface CommentaryDao {
    Commentary getById(long id);

    List<Commentary> getAll();

    Commentary save(Commentary commentary);

    Commentary update(Commentary commentary);

    void delete(Commentary commentary);
}
