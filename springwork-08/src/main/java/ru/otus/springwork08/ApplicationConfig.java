package ru.otus.springwork08;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.springwork08.event.CascadeSaveCommentaryEventListener;

@Component
public class ApplicationConfig {

    @Bean
    public CascadeSaveCommentaryEventListener userCascadingMongoEventListener() {
        return new CascadeSaveCommentaryEventListener();
    }

}
