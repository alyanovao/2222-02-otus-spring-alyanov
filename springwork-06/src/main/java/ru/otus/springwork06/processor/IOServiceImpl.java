package ru.otus.springwork06.processor;

import org.springframework.stereotype.Repository;

import java.io.PrintStream;
import java.util.Scanner;

@Repository
public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl() {
        output = new PrintStream(System.out);
        input = new Scanner(System.in);
    }

    @Override
    public String readStringWithPrompt(String message) {
        outputMessage(message);
        return input.nextLine();
    }

    @Override
    public long readLongWithPrompt(String message) {
        outputMessage(message);
        String inputString = input.nextLine();
        return Long.parseLong(inputString);
    }

    @Override
    public void outputMessage(String message) {
        output.println(message);
    }
}
