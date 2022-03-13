package ru.otus.service;

import ru.otus.domain.Examination;

import java.util.List;

public interface ExaminationService {
    Examination getExaminationById(int id);
    List<Examination> getExaminations();
}
