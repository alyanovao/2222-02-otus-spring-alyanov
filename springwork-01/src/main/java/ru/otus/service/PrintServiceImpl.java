package ru.otus.service;

import ru.otus.domain.Examination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintServiceImpl implements PrintService {

    @Override
    public void print(Examination examination) {
        List<String> list = new ArrayList<String>();
        list.addAll(examination.getRightAnswer());
        list.addAll(examination.getMistakeAnswer());

        StringBuilder sb = new StringBuilder();
        sb.append("Examination").append("\n")
                .append("id=").append(examination.getId()).append("\n")
                .append("question: ").append(examination.getQuestion()).append("\n");

        list.stream().forEach(c -> sb.append("answer: " + c + "\n"));
        System.out.println(sb.toString());
    }

    @Override
    public void print(List<Examination> examinations) {
        StringBuilder sb = new StringBuilder();
        sb.append("Examination").append("\n");

        for (Examination examination : examinations) {
            List<String> list = Stream.concat(examination.getRightAnswer().stream(),
                    examination.getMistakeAnswer().stream()).collect(Collectors.toList());
            sb.append("id=").append(examination.getId()).append("\n")
            .append("question: ").append(examination.getQuestion()).append("\n");
            list.stream().forEach(c -> sb.append("answer: " + c).append("\n"));
        }
        System.out.println(sb.toString());
    }
}
