package ru.otus.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.CommonUtil;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@PropertySource(value = "classpath:application.yml")
public class QuestionDaoSimple implements QuestionDao {

    @Value("${filepath}")
    private String filepath;

    @Override
    public Question getQuestionById(int id) {
        var questions = parseResource(filepath);
        var question = questions.stream()
                .filter(parameter -> id == parameter.getId())
                .findFirst()
                .orElse(null);
        return question;
    }

    @Override
    public int getSize() {
        var questions = parseResource(filepath);
        return questions.size();
    }

    private List<Question> parseResource(String path) {
        var question = new ArrayList<Question>();
        try {
            Resource resource = new ClassPathResource(path);
            InputStreamReader input = new InputStreamReader(resource.getInputStream());
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').parse(input);
            for (CSVRecord record : csvParser) {
                Question exam = new Question(
                        Integer.parseInt(record.get(CommonUtil.QUESTION_ID)),
                        record.get(CommonUtil.QUESTION),
                        Arrays.asList(record.get(CommonUtil.RIGHT_ANSWER).split(CommonUtil.SEPARATOR)),
                        Arrays.asList(record.get(CommonUtil.MISSING_ANSWER).split(CommonUtil.SEPARATOR))
                );
                question.add(exam);
            }
        }
        catch (IOException e) {
            System.out.println(String.format("Exceprion :: %s \n" +
                    "Method :: %s \n" +
                    " stackTrace :: %s", e.getClass(), Arrays.stream(getClass().getMethods()).findFirst(), e.getMessage()));
        }
        return question;
    }
}