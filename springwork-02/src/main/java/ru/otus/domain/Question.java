package ru.otus.domain;

import java.util.ArrayList;
import java.util.List;

//пробуем иммутабельность
public final class Question {
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

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getMistakeAnswer() {
        return mistakeAnswer;
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
