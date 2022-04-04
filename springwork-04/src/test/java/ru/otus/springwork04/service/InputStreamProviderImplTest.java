package ru.otus.springwork04.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InputStreamProviderImplTest {

    InputStreamProvider provider;

    @BeforeEach
    void setUp() {
        provider = new InputStreamProviderImpl(new AppConfigImpl("ru-RU", "questions-en.csv", "questions-ru.csv"));
    }

    @Test
    void readRawData() {
        assertThat(provider.readRawData()).isInstanceOf(InputStreamReader.class).isNotNull();
    }
}