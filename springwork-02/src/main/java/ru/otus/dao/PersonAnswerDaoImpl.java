package ru.otus.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.PersonAnswer;

import java.util.List;

@Repository
@PropertySource(value = "classpath:application.yml")
public class PersonAnswerDaoImpl implements PersonAnswerDao {

    @Value("${exelentGrade}")
    private int exelentGrade;

    @Value("${goodGrade}")
    private int goodGrade;

    @Value("${normalGrade}")
    private int normalGrade;

    @Value("${badGrade}")
    private int badGrade;

    @Override
    public int examine(List<PersonAnswer> tickets) {
        var rightAnswer = 0;
        for (PersonAnswer ticket : tickets) {
            rightAnswer = rightAnswer + countRightAnswer(ticket.getQuestion(), ticket.getAnswer());
        }
        return rightAnswer;
    }

    private int countRightAnswer(List<String> question, List<String> answer) {
        return (CollectionUtils.isEqualCollection(question, answer) ? 1 : 0);
    }

    @Override
    public int checkResult(int count) {
        if (count >= exelentGrade) {
            return exelentGrade;
        } else if (count >= goodGrade) {
            return goodGrade;
        } else if (count >= normalGrade) {
            return normalGrade;
        } else return badGrade;
    }
}
