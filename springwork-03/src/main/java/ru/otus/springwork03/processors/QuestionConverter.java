package ru.otus.springwork03.processors;

import ru.otus.springwork03.domain.Question;

import java.io.InputStreamReader;
import java.util.List;

public interface QuestionConverter {
    List<Question> convertDataToListOfStrings(InputStreamReader input);
}
