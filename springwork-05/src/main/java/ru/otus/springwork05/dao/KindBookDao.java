package ru.otus.springwork05.dao;

import ru.otus.springwork05.model.KindBook;

import java.util.List;

public interface KindBookDao {
    KindBook getById(long id);

    KindBook getByName(String name);

    void insert(KindBook kind);

    void delete(long id);

    long getEmptyId();
    List<KindBook> getAll();
}