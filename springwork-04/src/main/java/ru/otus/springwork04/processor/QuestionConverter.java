package ru.otus.springwork04.processor;

import ru.otus.springwork04.domain.Question;

import java.io.InputStreamReader;
import java.util.List;

public interface QuestionConverter {
    List<Question> convertDataToListOfStrings(InputStreamReader input);
}
