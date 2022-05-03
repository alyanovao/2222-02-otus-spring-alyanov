package ru.otus.springwork05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String partonymic;
}
