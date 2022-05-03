package ru.otus.springwork05.relation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BookAuthorRelation {
    private final long bookId;
    private final long authorId;
}
