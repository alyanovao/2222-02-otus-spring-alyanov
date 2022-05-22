package ru.otus.springwork06.processor;

public interface InputService {
    String readStringWithPrompt(String message);
    long readLongWithPrompt(String message);
}
