package ru.otus.springwork03.service;

import ru.otus.springwork03.domain.Person;
import ru.otus.springwork03.domain.Question;
import ru.otus.springwork03.domain.Student;

import java.util.List;

public interface StudentService {
    Person getPerson();
    List<String> inputStudentAnswer(Question question);
    void printResult(Student student);
    void printMessage(String message);
}
