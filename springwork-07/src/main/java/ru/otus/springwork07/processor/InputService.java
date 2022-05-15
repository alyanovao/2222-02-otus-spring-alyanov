package ru.otus.springwork07.processor;

public interface InputService {
    String readStringWithPrompt(String message);
    long readLongWithPrompt(String message);
}
