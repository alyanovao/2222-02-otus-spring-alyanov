package ru.otus.springwork04.processor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import ru.otus.springwork04.common.CommonUtil;
import ru.otus.springwork04.domain.Question;
import ru.otus.springwork04.exceptions.ConverterRuntimeException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionConverterImpl implements QuestionConverter {
    @Override
    public List<Question> convertDataToListOfStrings(InputStreamReader input) {
        List<Question> questions = new ArrayList<>();
        try {
            CSVParser rawData = CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').parse(input);
            for (CSVRecord questionCsv : rawData) {
                Question exam = new Question(
                        Integer.parseInt(questionCsv.get(CommonUtil.QUESTION_ID)),
                        questionCsv.get(CommonUtil.QUESTION),
                        Arrays.asList(questionCsv.get(CommonUtil.RIGHT_ANSWER).split(CommonUtil.SEPARATOR)),
                        Arrays.asList(questionCsv.get(CommonUtil.MISSING_ANSWER).split(CommonUtil.SEPARATOR))
                );
                questions.add(exam);
            }
            return questions;
        }
        catch (IOException e) {
            throw new ConverterRuntimeException(e);
        }
    }
}
