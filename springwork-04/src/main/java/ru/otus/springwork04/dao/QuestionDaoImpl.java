package ru.otus.springwork04.dao;

import org.springframework.stereotype.Repository;
import ru.otus.springwork04.domain.Question;
import ru.otus.springwork04.processor.QuestionConverter;
import ru.otus.springwork04.service.InputStreamProvider;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final InputStreamProvider source;
    private final QuestionConverter convertor;

    public QuestionDaoImpl(InputStreamProvider source, QuestionConverter convertor) {
        this.source = source;
        this.convertor = convertor;
    }

    @Override
    public Question getQuestionById(int id) {
        var questions = convertor.convertDataToListOfStrings(source.readRawData());
        return questions.stream()
                .filter(parameter -> id == parameter.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getSize() {
        var questions = convertor.convertDataToListOfStrings(source.readRawData());
        return questions.size();
    }
}
