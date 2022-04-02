package ru.otus.springwork03.processors;

import java.util.List;

public interface InputService {
    List<String> readListWithPrompt(String message);
    String readStringWithPrompt(String message);
}
