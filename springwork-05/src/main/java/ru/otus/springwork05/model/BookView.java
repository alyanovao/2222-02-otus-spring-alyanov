package ru.otus.springwork05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BookView {
    private final long id;
    private final String bookName;
    private final String authorName;
    private final String kindBook;
}
