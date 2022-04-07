package ru.otus.springwork04.service;

import ru.otus.springwork04.domain.StudentAnswer;

import java.util.List;

public interface CheckService {
    int examine(List<StudentAnswer> answers);
    int checkResult(int count);
}
