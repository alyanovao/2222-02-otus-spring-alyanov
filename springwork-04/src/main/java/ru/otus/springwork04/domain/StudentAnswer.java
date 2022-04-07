package ru.otus.springwork04.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class StudentAnswer {
    private final List<String> question;
    private final List<String> answer;
}