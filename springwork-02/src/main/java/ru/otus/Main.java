package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.ExaminationService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExaminationService service = context.getBean("examinationService", ExaminationService.class);
        service.runExam();
    }
}
