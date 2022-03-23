package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.PersonAnswerDao;
import ru.otus.domain.PersonAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class CheckServiceImplTest {

    @Mock
    private PersonAnswerDao dao;
    private CheckService checkService;

    @BeforeEach
    void setUp() {
        checkService = new CheckServiceImpl(dao);
    }


    @Test
    void checkQuestion() {
        PersonAnswer answer = new PersonAnswer(new ArrayList<>(Arrays.asList("Test")), new ArrayList<>(Arrays.asList("Test")));
        List<PersonAnswer> answers = new ArrayList<>();
        answers.add(answer);
        int grade = checkService.examine(answers);
        assertEquals(0, checkService.examine(answers));
    }

    @Test
    void examine() {
        assertThat(checkService.examine(new ArrayList<>(Arrays.asList(new PersonAnswer(Arrays.asList("T"), new ArrayList<>(Arrays.asList("T")))))))
                .isNotNull();
    }

    @Test
    void checkResult() {
        assertEquals(0, checkService.checkResult(1));
    }
}