package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.PersonAnswerDao;
import ru.otus.dao.PersonDao;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.PersonAnswer;

import java.util.ArrayList;
import java.util.List;

@Service("examinationService")
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final QuestionDao dao;
    private final PersonDao personDao;
    private final PrintService printService;
    private final ReadService readService;
    private final PersonAnswerDao personAnswerDao;

    @Override
    public void runExam() {
        final Person person = personDao.inputPerson();
        List<PersonAnswer> answer = new ArrayList<>();
        for (int i = 1; i < dao.getSize() + 1; i++) {
            printService.printQuestion(i);
            answer.add(new PersonAnswer(readService.inputAnswer(i), dao.getQuestionById(i).getRightAnswer()));
        }
        person.setCountRightAnswer(personAnswerDao.examine(answer));
        person.setGrade(personAnswerDao.checkResult(person.getCountRightAnswer()));
        printService.printResult(person);
    }
}
