package ru.otus.springwork04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springwork04.service.ExaminationService;

@SpringBootApplication
public class Springwork04Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Springwork04Application.class, args);
        ExaminationService service = ctx.getBean(ExaminationService.class);
        service.runExam();
    }
}