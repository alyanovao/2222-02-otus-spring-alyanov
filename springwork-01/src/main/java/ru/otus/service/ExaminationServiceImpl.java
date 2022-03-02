package ru.otus.service;

import ru.otus.dao.ExaminationDao;
import ru.otus.domain.Examination;

import java.util.List;

public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationDao dao;

    public ExaminationServiceImpl(ExaminationDao dao) {
        this.dao = dao;
    }

    @Override
    public Examination getExaminationById(int id) {
        return dao.getExaminationById(id);
    }

    @Override
    public List<Examination> getExaminations() {
        return dao.getExaminations();
    }
}
