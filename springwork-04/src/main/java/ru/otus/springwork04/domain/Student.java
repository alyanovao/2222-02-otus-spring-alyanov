package ru.otus.springwork04.domain;

import lombok.Getter;

@Getter
public class Student extends Person {
    private final int countRightAnswer;
    private final int grade;

    public Student(String name, String lastName, int countRightAnswer, int grade) {
        super(name, lastName);
        this.countRightAnswer = countRightAnswer;
        this.grade = grade;
    }
}
