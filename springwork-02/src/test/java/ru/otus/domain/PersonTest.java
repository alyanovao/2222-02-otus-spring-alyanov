package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Person")
class PersonTest {

    @Test
    @DisplayName("Корректно иниализируется объект и выполняются сеттеры")
    void shouldHaveCorrectConstructor() {
        Person person = new Person();
        person.setName("Ivan");
        person.setLastName("Ivanov");
        person.setCountRightAnswer(1);
        person.setGrade(5);
        assertEquals("Ivan", person.getName());
        assertEquals("Ivanov", person.getLastName());
        assertEquals(1, person.getCountRightAnswer());
        assertEquals(5, person.getGrade());
    }

}