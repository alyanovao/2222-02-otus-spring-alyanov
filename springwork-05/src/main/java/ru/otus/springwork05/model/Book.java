package ru.otus.springwork05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Book {
    private final long id;
    private final String name;
    private final List<Author> authors;
    private final List<KindBook> kindBooks;
}
