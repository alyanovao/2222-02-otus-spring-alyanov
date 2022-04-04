package ru.otus.springwork04.dao;

import ru.otus.springwork04.domain.StudentAnswer;

import java.util.List;

public interface StudentAnswerHandler {
    int examine(List<StudentAnswer> answers);
    int checkResult(int count);
}
