package ru.otus.service;

import ru.otus.domain.Examination;

import java.util.List;

public interface PrintService {
    void print(Examination examination);
    void print(List<Examination> examinations);
}
