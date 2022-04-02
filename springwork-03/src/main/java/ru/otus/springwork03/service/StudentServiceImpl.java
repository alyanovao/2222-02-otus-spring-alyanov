package ru.otus.springwork03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.springwork03.dao.MessageSourceHandler;
import ru.otus.springwork03.domain.Person;
import ru.otus.springwork03.domain.Question;
import ru.otus.springwork03.domain.Student;
import ru.otus.springwork03.exceptions.InputRuntimeException;
import ru.otus.springwork03.exceptions.NotFilledStudentException;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService service;
    private final MessageSourceHandler messageSource;
    private final QuestionStopService questionStop;

    @Override
    public Person getPerson() {
        service.outputMessage(messageSource.getMessage("string.greeting"));
        String name = service.readStringWithPrompt(messageSource.getMessage("string.input-name"));
        String lastName = service.readStringWithPrompt(messageSource.getMessage("string.input-lastname"));
        if (name.isEmpty() && lastName.isEmpty()) {
            throw new NotFilledStudentException("Not filled name or lastname");
        }
        return new Person(name, lastName);
    }

    @Override
    public List<String> inputStudentAnswer(Question question) {
        var questions = new ArrayList<>();
        questions.addAll(question.getRightAnswer());
        questions.addAll(question.getMistakeAnswer());

        StringBuilder sb = new StringBuilder();
        sb.append(messageSource.getMessage("string.examination")).append("\n")
                .append(messageSource.getMessage("string.id")).append(question.getId()).append("\n")
                .append(messageSource.getMessage("string.question")).append(question.getQuestionText()).append("\n");
        questions.stream().forEach(c -> sb.append(messageSource.getMessage("string.answer") + c + "\n"));
        service.outputMessage(sb.toString());

        List<String> studentAnswer = service.readListWithPrompt(messageSource.getMessage("string.input-answer"));
        boolean existAnswer = questions.stream().anyMatch(studentAnswer::contains);
        boolean isStopQuestion = studentAnswer.contains("NO");
        if (existAnswer == false && isStopQuestion == false) {
            throw new InputRuntimeException("answer not found");
        }
        questionStop.stopAskQuestion();
        return studentAnswer;
    }

    @Override
    public void printResult(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(messageSource.getMessage("string.respected")).append(student.getName()).append(" ").append(student.getLastName()).append("\n");
        sb.append(messageSource.getMessage("string.result")).append(student.getCountRightAnswer()).append("\n");
        sb.append(messageSource.getMessage("string.grade")).append(student.getGrade());
        service.outputMessage(sb.toString());
    }

    @Override
    public void printMessage(String message) {
        service.outputMessage(message);
    }
}