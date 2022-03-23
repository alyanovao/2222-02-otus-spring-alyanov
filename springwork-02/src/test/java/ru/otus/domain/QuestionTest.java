package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс Question")
class QuestionTest {

    @Test
    @DisplayName("Корректно инициализируется конструктор")
    void shouldHaveCorrectConstructor() {
        Question question = new Question(1, "questionTest", new ArrayList<String>(Arrays.asList("answer", "answer2")), new ArrayList<>());
        assertEquals("questionTest", question.getQuestionText());
    }

    @Test
    @DisplayName("Корректно работает метод toString")
    void testToString() {
        Question question = new Question(1, "questionTest", new ArrayList<String>(Arrays.asList("answer", "answer2")), new ArrayList<>());
        assertThat(question.getQuestionText().equals("questionTest"));
        assertThat(question.getRightAnswer()).isNotNull();
    }
}