package ru.otus.springwork03.dao;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ListResourceBundle;
import java.util.Locale;

//@SpringBootConfiguration
//@EnableConfigurationProperties
//@ComponentScan({"ru.otus.springwork03.dao", "ru.otus.springwork03.service", "ru.otus.springwork03.processors"})
//Не работает
public class TestContextConfig {

    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource
                = new ResourceBundleMessageSource();

        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /*public class Messages extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][] {
                    {"string.answer", "answers:"}};
        };
    }

    public class Messages_ru_RU extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][] {
                    {"string.answer", "тест"}};
        };
    }*/
}
