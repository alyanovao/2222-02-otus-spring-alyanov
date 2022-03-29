package ru.otus.springwork03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springwork03.dao.MessageSourceHandler;
import ru.otus.springwork03.dao.QuestionDao;
import ru.otus.springwork03.dao.StudentAnswerHandler;
import ru.otus.springwork03.domain.Person;
import ru.otus.springwork03.domain.Student;
import ru.otus.springwork03.domain.StudentAnswer;
import ru.otus.springwork03.exceptions.InputRuntimeException;

import java.util.ArrayList;
import java.util.List;

@Service("examinationService")
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final QuestionDao dao;
    private final StudentService service;
    private final StudentAnswerHandler studentAnswerHandler;
    private final QuestionStopService stopService;
    private final MessageSourceHandler messageSource;

    @Override
    public void runExam() {

        Person person = service.getPerson();

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
}
