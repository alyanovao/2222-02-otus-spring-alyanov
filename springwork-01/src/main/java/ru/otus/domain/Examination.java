package ru.otus.domain;

import java.util.List;

public class Examination {
    private int id;
    private String question;
    private List<String> rightAnswer;
    private List<String> mistakeAnswer;

    public Examination() { super(); }

    public Examination(int id, String question, List<String> rightAnswer, List<String> mistakeAnswer) {
        this.id = id;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.mistakeAnswer = mistakeAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(List<String> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getMistakeAnswer() {
        return mistakeAnswer;
    }

    public void setMistakeAnswer(List<String> mistakeAnswer) {
        this.mistakeAnswer = mistakeAnswer;
    }
}
