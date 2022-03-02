package ru.otus.dao;

import ru.otus.domain.Examination;

import java.util.List;

public interface ExaminationDao {
    Examination getExaminationById(int id);
    List<Examination> getExaminations();
}