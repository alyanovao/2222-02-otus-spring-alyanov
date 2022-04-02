package ru.otus.springwork03.service;

import org.springframework.stereotype.Repository;
import ru.otus.springwork03.common.CommonUtil;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams() {
        output = new PrintStream(System.out);
        input = new Scanner(System.in);
    }

    @Override
    public void outputMessage(String message) {
        output.println(message);
    }

    @Override
    public List<String> readListWithPrompt(String message) {
        outputMessage(message);
        return Arrays.asList(input.nextLine().split(CommonUtil.SEPARATOR));
    }

    @Override
    public String readStringWithPrompt(String message) {
        outputMessage(message);
        return input.nextLine();
    }
}