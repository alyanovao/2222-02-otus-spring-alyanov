package ru.otus.springwork08.repository;

import ru.otus.springwork08.model.KindBook;

public interface KindBookRepositoryCustom {
    KindBook getById(String id);
}
