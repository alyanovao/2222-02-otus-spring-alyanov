package ru.otus.springwork04.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springwork04.dao.MessageSourceHandler;
import ru.otus.springwork04.dao.QuestionDao;
import ru.otus.springwork04.dao.StudentAnswerHandler;
import ru.otus.springwork04.domain.StudentAnswer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ExaminationServiceImplTest {

    @Autowired
    private QuestionDao dao;

    @MockBean
    private StudentService service;

    @Autowired
    private StudentAnswerHandler studentAnswerHandler;

    @MockBean
    private QuestionStopService stopService;

    @MockBean
    private MessageSourceHandler messageSource;

    @MockBean
    private AuthorizationService authorizationService;

    @Test
    void runExam() {
        var count = dao.getSize();
        List<StudentAnswer> answer = new ArrayList<>();
        for (int i = 1; i < count+1; i++) {
            final List<String> answerOfQuestion = new ArrayList<>();
            answerOfQuestion.add(String.valueOf(12742));
            List<String> righAnswer = dao.getQuestionById(i).getRightAnswer();
            answer.add(new StudentAnswer(answerOfQuestion, righAnswer));
        }

        int countAnswer = studentAnswerHandler.examine(answer);
        assertThat(countAnswer).isEqualTo(1);
    }
}