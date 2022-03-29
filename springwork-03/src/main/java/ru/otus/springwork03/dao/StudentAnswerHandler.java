package ru.otus.springwork03.dao;

import ru.otus.springwork03.domain.StudentAnswer;

import java.util.List;

public interface StudentAnswerHandler {
    int examine(List<StudentAnswer> answers);
    int checkResult(int count);
}
