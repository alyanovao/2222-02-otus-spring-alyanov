package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Examination")
public class ExaminationTest {

    @DisplayName("коректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Examination examination = new Examination(1, "Test", new ArrayList<>(), new ArrayList<>());
        assertEquals(1, examination.getId());
        assertEquals("Test", examination.getQuestion());
    }
}
