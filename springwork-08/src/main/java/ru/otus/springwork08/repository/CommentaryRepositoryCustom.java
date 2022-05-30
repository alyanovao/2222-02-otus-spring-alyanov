package ru.otus.springwork08.repository;

import ru.otus.springwork08.model.Commentary;

public interface CommentaryRepositoryCustom {
    Commentary getById(String id);
}
