package ru.otus.service;

import org.springframework.stereotype.Component;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.Question;

import java.util.ArrayList;

@Component
public class PrintServiceImpl implements PrintService {
    private final QuestionDao dao;

    public PrintServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public void printResult(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(person.getName()).append(" ").append(person.getLastName()).append("\n");
        sb.append("result: ").append(person.getCountRightAnswer()).append("\n");
        sb.append("Your grade: ").append(person.getGrade());
        System.out.println(sb.toString());
    }

    @Override
    public void printQuestion(int i) {
        Question question = dao.getQuestionById(i);
        var questions = new ArrayList<>();
        questions.addAll(question.getRightAnswer());
        questions.addAll(question.getMistakeAnswer());

        StringBuilder sb = new StringBuilder();
        sb.append("Examination").append("\n")
                .append("Id: ").append(i).append("\n")
                .append("question: ").append(question.getQuestionText()).append("\n");
        questions.stream().forEach(c -> sb.append("answer: " + c + "\n"));
        System.out.println(sb.toString());
    }
}
