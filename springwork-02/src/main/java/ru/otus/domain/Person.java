package ru.otus.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String name;
    private String lastName;
    private int countRightAnswer;
    private int grade;

}


