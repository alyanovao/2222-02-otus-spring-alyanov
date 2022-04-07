package ru.otus.springwork04.service;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springwork04.dao.MessageSourceHandler;
import ru.otus.springwork04.domain.Question;
import ru.otus.springwork04.exceptions.InputRuntimeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StudentServiceImplTest {

    @MockBean
    private IOService service;

    @MockBean
    MessageSourceHandler source;

    @MockBean
    QuestionStopService stopService;

    @Autowired
    StudentService testService;

    @BeforeEach
    void setUp() {
        given(source.getMessage("Test")).willReturn(String.valueOf(Optional.of("Test")));
    }

    @Test
    void getPerson() {
        assertThat(service.readListWithPrompt("")).isNotNull();
    }

    @Test
    void inputStudentAnswer() {
        Question question = new Question(1, "questionTest", new ArrayList<String>(Arrays.asList("answer", "answer2")),
                new ArrayList<String>(Arrays.asList("answer", "answer2")));
        assertThatThrownBy(() -> testService.inputStudentAnswer(question)).isInstanceOf(InputRuntimeException.class);
    }
}