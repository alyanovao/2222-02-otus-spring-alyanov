package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Examination;
import ru.otus.service.ExaminationService;
import ru.otus.service.PrintService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExaminationService service = context.getBean(ExaminationService.class);
        PrintService printService = context.getBean(PrintService.class);

        List<Examination> list = service.getExaminations();
        printService.print(list);
    }

}