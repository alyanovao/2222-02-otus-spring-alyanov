package ru.otus.springwork08.repository;

import ru.otus.springwork08.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    Book getById(String id);
    List<Book> getAllByFirstNameAndKind(String firstName, String kind);
}