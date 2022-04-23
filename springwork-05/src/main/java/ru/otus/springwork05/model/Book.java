package ru.otus.springwork05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Book {
    private final long id;
    private final String name;
    private final long authorId;
    private final long kindId;
}
