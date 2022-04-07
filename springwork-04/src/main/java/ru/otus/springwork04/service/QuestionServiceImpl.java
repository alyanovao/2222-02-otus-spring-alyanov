package ru.otus.springwork04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springwork04.dao.QuestionDao;
import ru.otus.springwork04.domain.Question;

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
