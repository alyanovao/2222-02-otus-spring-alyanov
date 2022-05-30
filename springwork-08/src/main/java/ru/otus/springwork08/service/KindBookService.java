package ru.otus.springwork08.service;

import ru.otus.springwork08.model.KindBook;

import java.util.List;
import java.util.Optional;

public interface KindBookService {
    Optional<KindBook> findById(String id);
    List<KindBook> findAll();
    KindBook save(KindBook kind);
    KindBook merge(KindBook kind);
}
