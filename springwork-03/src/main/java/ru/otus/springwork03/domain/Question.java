package ru.otus.springwork03.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Question {
    private final int id;
    private final String questionText;
    private final List<String> rightAnswer;
    private final List<String> mistakeAnswer;

    public Question(int id, String questionText, List<String> rightAnswer, List<String> mistakeAnswer) {
        this.id = id;
        this.questionText = questionText;

        List<String> rightAnswerCopy = new ArrayList<>();
        for (String answerElement : rightAnswer) {
            rightAnswerCopy.add(answerElement);
        }

        this.rightAnswer = rightAnswerCopy;

        List<String> mistakeAnswerCopy = new ArrayList<>();
        for (String answerElement : mistakeAnswer) {
            mistakeAnswerCopy.add(answerElement);
        }
        this.mistakeAnswer = mistakeAnswerCopy;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", question='" + questionText + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", mistakeAnswer=" + mistakeAnswer +
                '}';
    }
}