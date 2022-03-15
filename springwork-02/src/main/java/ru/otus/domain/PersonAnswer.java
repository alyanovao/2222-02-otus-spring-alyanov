package ru.otus.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class PersonAnswer {
    private final List<String> question;
    private final List<String> answer;
}
