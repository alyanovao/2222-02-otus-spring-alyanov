package ru.otus.service;

import org.springframework.stereotype.Component;
import ru.otus.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class ReadServiceImpl implements ReadService {

    @Override
    public List<String> inputAnswer(int num) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input answer or some amswers with ','");
        try {
            return Arrays.asList(reader.readLine().split(CommonUtil.SEPARATOR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
