package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


@DisplayName("Класс PersonDaoSimple")
@PropertySource(value = "classpath:application.yml")
@ExtendWith(MockitoExtension.class)
class PersonDaoSimpleTest {

    @Value("${filepath}")
    private String filepath;

    @Test
    void inputPerson() {
        //Person
    }
}