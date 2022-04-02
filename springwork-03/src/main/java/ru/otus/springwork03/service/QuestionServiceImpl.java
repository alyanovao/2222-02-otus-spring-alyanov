package ru.otus.springwork03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springwork03.dao.QuestionDao;
import ru.otus.springwork03.domain.Question;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    @Override
    public Question getQuestionById(int id) {
        return dao.getQuestionById(id);
    }

    @Override
    public int getSize() {
        return dao.getSize();
    }
}
