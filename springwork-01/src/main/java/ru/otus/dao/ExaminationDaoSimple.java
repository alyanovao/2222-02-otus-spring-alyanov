package ru.otus.dao;

import ru.otus.domain.Examination;
import ru.otus.util.ResourceUtil;

import java.util.List;

public class ExaminationDaoSimple implements ExaminationDao {

    private final String path;

    public ExaminationDaoSimple(String path) {
        this.path = path;
    }

    @Override
    public Examination getExaminationById(int id) {
        List<Examination> examinations = ResourceUtil.parseResource(path);
        Examination examination = examinations.stream()
                .filter(parameter -> id == parameter.getId())
                .findFirst()
                .orElse(null);
        return examination;
    }

    @Override
    public List<Examination> getExaminations() {
        return ResourceUtil.parseResource(path);
    }
}
