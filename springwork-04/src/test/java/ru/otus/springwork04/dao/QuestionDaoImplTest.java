package ru.otus.springwork04.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.springwork04.processor.QuestionConverterImpl;
import ru.otus.springwork04.service.AppConfigImpl;
import ru.otus.springwork04.service.InputStreamProviderImpl;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Дао получения вопросов")
class QuestionDaoImplTest {

    private static final int COUNT_EXECUTE = 5;
    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl(new InputStreamProviderImpl(new AppConfigImpl("ru-RU", "questions-en.csv", "questions-ru.csv")),
                new QuestionConverterImpl());
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("Должен возвращать не пустое значение")
    void getQuestionById(int num, String questionText) {
        Optional<String> questionOptional = Optional.ofNullable(questionDao.getQuestionById(num).getQuestionText());
        assertThat(questionOptional).isNotEmpty().hasValue(questionText);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(1, "whats length of earth"),
                Arguments.of(2, "whats the temperature today"),
                Arguments.of(3, "what brand of car was invented by Henry Ford"),
                Arguments.of(4, "in which work is NIICHAVO mentioned"),
                Arguments.of(5, "how mach is the fish")

        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("Должен возвращать количество вопросов")
    void getSize() {
        var lenght = Optional.ofNullable(questionDao.getSize());
        assertThat(questionDao).isNotNull();
        assertThat(lenght.get()).isEqualTo(COUNT_EXECUTE);
    }
}