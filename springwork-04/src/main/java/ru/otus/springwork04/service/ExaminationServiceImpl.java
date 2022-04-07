package ru.otus.springwork04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.stereotype.Service;
import ru.otus.springwork04.dao.MessageSourceHandler;
import ru.otus.springwork04.dao.QuestionDao;
import ru.otus.springwork04.dao.StudentAnswerHandler;
import ru.otus.springwork04.domain.Person;
import ru.otus.springwork04.domain.Student;
import ru.otus.springwork04.domain.StudentAnswer;
import ru.otus.springwork04.exceptions.InputRuntimeException;

import java.util.ArrayList;
import java.util.List;

@Service("examinationService")
@RequiredArgsConstructor
@ShellComponent
public class ExaminationServiceImpl implements ExaminationService {

    private final QuestionDao dao;
    private final StudentService service;
    private final StudentAnswerHandler studentAnswerHandler;
    private final QuestionStopService stopService;
    private final MessageSourceHandler messageSource;
    private final AuthorizationService authorizationService;
    private Person person;

    @Override
    @ShellMethod(value = "Exam", key = {"exam", "e"})
    @ShellMethodAvailability(value = "isLoginPerson")
    public void runExam() {

        List<StudentAnswer> answer = new ArrayList<>();

        for (int i = 1; i < dao.getSize() + 1; i++) {
            final List<String> questions2student = new ArrayList<>();
            stopService.startAskQuestion();
            questions2student.addAll(dao.getQuestionById(i).getRightAnswer());
            questions2student.addAll(dao.getQuestionById(i).getMistakeAnswer());
            final List<String> answerOfQuestion = new ArrayList<>();
            while(stopService.isWatingAnswerForQuestion()) {
                try {
                    answerOfQuestion.addAll(service.inputStudentAnswer(dao.getQuestionById(i)));
                }
                catch (InputRuntimeException e) {
                    service.printMessage(messageSource.getMessage("string.missing-answer"));
                }
            }
            answer.add(new StudentAnswer(answerOfQuestion, dao.getQuestionById(i).getRightAnswer()));
        }
        int countAnswer = studentAnswerHandler.examine(answer);
        int grade = studentAnswerHandler.checkResult(countAnswer);
        Student student = new Student(person.getName(), person.getLastName(), countAnswer, grade);
        service.printResult(student);
    }

    @Override
    @ShellMethod(value = "login", key = {"login", "l"})
    public Person login() {
        person = authorizationService.login();
        return person;
    }

    private Availability isLoginPerson() {
        return person == null ?Availability.unavailable("you are anonym, please login") : Availability.available();
    }
}
