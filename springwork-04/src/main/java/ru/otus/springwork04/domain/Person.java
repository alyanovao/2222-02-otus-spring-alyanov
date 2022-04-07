package ru.otus.springwork04.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Person {
    private final String name;
    private final String lastName;
}