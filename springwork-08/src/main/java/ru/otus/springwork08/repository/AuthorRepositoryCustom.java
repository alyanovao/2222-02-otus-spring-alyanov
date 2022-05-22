package ru.otus.springwork08.repository;

import ru.otus.springwork08.model.Author;

public interface AuthorRepositoryCustom {
    Author getById(String id);
}
