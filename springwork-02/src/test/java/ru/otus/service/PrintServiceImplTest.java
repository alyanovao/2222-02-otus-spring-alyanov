package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Person;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {
    @Mock
    private QuestionDao dao;
    private PrintService service;

    @BeforeEach
    void setAp() {
        service = new PrintServiceImpl(dao);
    }


    @Test
    void printResult() {
        Person person = new Person();
        person.setName("Test");
        person.setLastName("Testov");
        service.printResult(new Person());
    }

    @Test
    void printQuestion() {
    }
}