package ru.otus.springwork05.relation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BookKindRelation {
    private final long bookId;
    private final long bookKindId;
}
