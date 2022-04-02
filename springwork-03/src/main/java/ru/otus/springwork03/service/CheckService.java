package ru.otus.springwork03.service;

import ru.otus.springwork03.domain.StudentAnswer;

import java.util.List;

public interface CheckService {
    int examine(List<StudentAnswer> answers);
    int checkResult(int count);
}
