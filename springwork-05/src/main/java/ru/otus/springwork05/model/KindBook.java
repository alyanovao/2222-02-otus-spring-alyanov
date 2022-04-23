package ru.otus.springwork05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KindBook {
    private final long id;
    private final String name;
}
