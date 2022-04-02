package ru.otus.springwork03.service;

import ru.otus.springwork03.domain.Question;

public interface QuestionService {
    Question getQuestionById(int id);
    int getSize();
}
