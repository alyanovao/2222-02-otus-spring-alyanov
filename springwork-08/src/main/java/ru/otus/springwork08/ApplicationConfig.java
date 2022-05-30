package ru.otus.springwork08;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.springwork08.event.CascadeSaveCommentaryEventListener;
import ru.otus.springwork08.event.CascadeUpdateAuthorEventListener;
import ru.otus.springwork08.event.CascadeUpdateKindBookEventListener;

@Component
public class ApplicationConfig {

    @Bean
    public CascadeSaveCommentaryEventListener userCascadingMongoEventListener() {
        return new CascadeSaveCommentaryEventListener();
    }

    @Bean
    public CascadeUpdateAuthorEventListener cascadeUpdateAuthorEventListener() {
        return new CascadeUpdateAuthorEventListener();
    }

    @Bean
    public CascadeUpdateKindBookEventListener cascadeUpdateKindBookEventListener() {
        return new CascadeUpdateKindBookEventListener();
    }
}