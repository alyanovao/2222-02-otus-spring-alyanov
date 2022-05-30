package ru.otus.springwork08.processor;

public interface InputService {
    String readStringWithPrompt(String message);
    long readLongWithPrompt(String message);
}
