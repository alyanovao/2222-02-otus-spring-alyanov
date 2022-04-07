package ru.otus.springwork04.dao;

import ru.otus.springwork04.domain.Question;

public interface QuestionDao {
    Question getQuestionById(int id);
    int getSize();
}
