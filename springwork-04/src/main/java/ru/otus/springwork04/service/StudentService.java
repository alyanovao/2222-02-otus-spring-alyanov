package ru.otus.springwork04.service;

import ru.otus.springwork04.domain.Person;
import ru.otus.springwork04.domain.Question;
import ru.otus.springwork04.domain.Student;

import java.util.List;

public interface StudentService {
    Person getPerson();
    List<String> inputStudentAnswer(Question question);
    void printResult(Student student);
    void printMessage(String message);
}
