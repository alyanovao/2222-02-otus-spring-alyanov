package ru.otus.springwork04.service;

public interface QuestionStopService {
    boolean isWatingAnswerForQuestion();
    void stopAskQuestion();
    void startAskQuestion();
}
