package ru.otus.springwork03.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.springwork03.common.CommonUtil;
import ru.otus.springwork03.service.AppConfigImpl;
import ru.otus.springwork03.service.LocalizationService;

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
        var language = CommonUtil.RUSSIAN_LANGUAGE;
        final Locale locale = Locale.forLanguageTag(language);
        var message = messageSource.getMessage("string.answer", null, locale);
        assertThat(message).isEqualTo("ответы:");
    }

    @Test
    void shouldReturnRightEngLocalization() {
        var language = CommonUtil.DEFAULT_LANGUAGE;
        final Locale locale = Locale.forLanguageTag(language);
        var message = messageSource.getMessage("string.answer", null, locale);
        assertThat(message).isEqualTo("answers:");
    }
}