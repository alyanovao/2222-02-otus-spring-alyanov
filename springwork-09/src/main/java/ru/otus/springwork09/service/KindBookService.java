package ru.otus.springwork09.service;

import ru.otus.springwork09.model.KindBook;

import java.util.List;
import java.util.Optional;

public interface KindBookService {
    Optional<KindBook> findById(long id);
    List<KindBook> findAll();
    KindBook save(KindBook kind);
}
