package ru.otus.springwork03.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class QuestionStopServiceImpl implements QuestionStopService {

    private final AtomicBoolean flag;

    public QuestionStopServiceImpl() {
        this.flag = new AtomicBoolean(true);
    }

    @Override
    public boolean isWatingAnswerForQuestion() {
        return flag.get();
    }

    @Override
    public void stopAskQuestion() {
        flag.set(false);
    }

    @Override
    public void startAskQuestion() {
        flag.set(true);
    }
}
