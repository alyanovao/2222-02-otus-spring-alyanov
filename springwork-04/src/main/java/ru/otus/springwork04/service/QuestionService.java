package ru.otus.springwork04.service;

import ru.otus.springwork04.domain.Question;

public interface QuestionService {
    Question getQuestionById(int id);
    int getSize();
}
