package ru.otus.springwork04.processor;

import java.util.List;

public interface InputService {
    List<String> readListWithPrompt(String message);
    String readStringWithPrompt(String message);
}
