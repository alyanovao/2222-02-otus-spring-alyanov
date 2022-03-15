package ru.otus.service;

import ru.otus.domain.PersonAnswer;

import java.util.List;

public interface CheckService {
    int examine(List<PersonAnswer> answers);
    int checkResult(int count);
}
