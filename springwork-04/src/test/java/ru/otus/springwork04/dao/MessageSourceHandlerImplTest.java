package ru.otus.springwork04.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.springwork04.service.AppConfigImpl;
import ru.otus.springwork04.service.LocalizationService;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Проверка сервиса получания сообщений")
@SpringBootTest
class MessageSourceHandlerImplTest {

    @Autowired
    private MessageSource messageSource;

    private LocalizationService appConfig;

    @Value("${localization.language}")
    private String language;

    @Value("${file.path.englishLocate}")
    private String filePathEn;

    @Value("${file.path.russianLocate}")
    private String filePathRu;

    private MessageSourceHandler messageHandler;

    @BeforeEach
    void setUp() {
        appConfig = new AppConfigImpl("en", filePathEn, filePathRu);
        messageHandler = new MessageSourceHandlerImpl(messageSource, appConfig);
    }

    @Test
    void shouldReturnRightRussianLocalization() {
        var language = "ru-RU";
        final Locale locale = Locale.forLanguageTag(language);
        var message = messageSource.getMessage("string.answer", null, locale);
        assertThat(message).isEqualTo("ответы:");
    }
}