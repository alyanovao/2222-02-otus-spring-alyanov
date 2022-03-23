package ru.otus.dao;

import ru.otus.domain.Question;

public interface QuestionDao {
    Question getQuestionById(int id);
    int getSize();
}
