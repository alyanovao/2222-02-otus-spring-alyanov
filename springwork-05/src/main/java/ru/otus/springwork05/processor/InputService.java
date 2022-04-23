package ru.otus.springwork05.processor;

public interface InputService {
    String readStringWithPrompt(String message);
    long readLongWithPrompt(String message);
}
