package ru.otus.dao;

import ru.otus.domain.PersonAnswer;

import java.util.List;

public interface PersonAnswerDao {
    int examine(List<PersonAnswer> answers);
    int checkResult(int count);
}
