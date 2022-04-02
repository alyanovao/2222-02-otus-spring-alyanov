package ru.otus.springwork03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.springwork03.service.ExaminationService;

@SpringBootApplication
public class Springwork03Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Springwork03Application.class, args);
        ExaminationService service = ctx.getBean(ExaminationService.class);
        service.runExam();
    }
}
