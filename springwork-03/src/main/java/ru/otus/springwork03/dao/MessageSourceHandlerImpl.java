package ru.otus.springwork03.dao;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.springwork03.common.CommonUtil;
import ru.otus.springwork03.service.LocalizationService;

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
        var languageProperty = appConfig.getLanguage().equals("ru") ? CommonUtil.RUSSIAN_LANGUAGE : CommonUtil.DEFAULT_LANGUAGE;
        final Locale locale = Locale.forLanguageTag(languageProperty);
        var message = messageSource.getMessage(key, null, locale);
        return message;
    }
}
