package ru.otus.springwork03.service;

public interface QuestionStopService {
    boolean isWatingAnswerForQuestion();
    void stopAskQuestion();
    void startAskQuestion();
}
