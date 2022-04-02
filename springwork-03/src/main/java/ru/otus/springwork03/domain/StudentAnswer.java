package ru.otus.springwork03.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class StudentAnswer {
    private final List<String> question;
    private final List<String> answer;
}