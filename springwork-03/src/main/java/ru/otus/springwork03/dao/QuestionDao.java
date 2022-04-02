package ru.otus.springwork03.dao;

import ru.otus.springwork03.domain.Question;

public interface QuestionDao {
    Question getQuestionById(int id);
    int getSize();
}
