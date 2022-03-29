package ru.otus.springwork03.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.springwork03.domain.StudentAnswer;

import java.util.List;

@Component
public class StudentAnswerHandlerImpl implements StudentAnswerHandler {

    @Value("${examine.excellentGrade}")
    private int excellentGrade;

    @Value("${examine.goodGrade}")
    private int goodGrade;

    @Value("${examine.normalGrade}")
    private int normalGrade;

    @Value("${examine.badGrade}")
    private int badGrade;

    @Override
    public int examine(List<StudentAnswer> tickets) {
        var rightAnswer = 0;
        for (StudentAnswer ticket : tickets) {
            rightAnswer = rightAnswer + countRightAnswer(ticket.getQuestion(), ticket.getAnswer());
        }
        return rightAnswer;
    }

    private int countRightAnswer(List<String> question, List<String> answer) {
        return (CollectionUtils.isEqualCollection(question, answer) ? 1 : 0);
    }

    @Override
    public int checkResult(int count) {
        if (count >= excellentGrade) {
            return excellentGrade;
        } else if (count >= goodGrade) {
            return goodGrade;
        } else if (count >= normalGrade) {
            return normalGrade;
        } else return badGrade;
    }
}
