package ru.otus.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.domain.Examination;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ResourceUtil {

    public static List<Examination> parseResource(String path) {
        List<Examination> examination = new ArrayList<Examination>();
        try {
            Resource resource = new ClassPathResource(path);
            InputStreamReader input = new InputStreamReader(resource.getInputStream());
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').parse(input);
            for (CSVRecord record : csvParser) {
                Examination exam = new Examination();
                exam.setId(Integer.parseInt(record.get(CommonUtil.QUESTION_ID)));
                exam.setQuestion(record.get(CommonUtil.QUESTION));
                exam.setRightAnswer(Arrays.asList(record.get(CommonUtil.RIGHT_ANSWER).split(CommonUtil.SEPARATOR)));
                exam.setMistakeAnswer(Arrays.asList(record.get(CommonUtil.MISSING_ANSWER).split(CommonUtil.SEPARATOR)));
                examination.add(exam);
            }
        }
        catch (IOException e) {
            System.out.println(String.format("Exceprion :: %s \n" +
                    "Method :: %s \n" +
                    " stackTrace :: %s", e.getClass(), ResourceUtil.class, e.getMessage()));
        }
        return examination;

    }

}
