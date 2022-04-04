package ru.otus.springwork04.dao;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.springwork04.service.LocalizationService;

import java.util.Locale;

@Component
public class MessageSourceHandlerImpl implements MessageSourceHandler {

    private final MessageSource messageSource;
    private final LocalizationService appConfig;

    public MessageSourceHandlerImpl(MessageSource messageSource, LocalizationService appConfig) {
        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    @Override
    public String getMessage(String key) {
        var languageProperty = appConfig.getLanguage();
        final Locale locale = Locale.forLanguageTag(languageProperty);
        var message = messageSource.getMessage(key, null, locale);
        return message;
    }
}
