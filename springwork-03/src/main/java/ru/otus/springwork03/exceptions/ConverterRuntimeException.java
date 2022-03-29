package ru.otus.springwork03.exceptions;

import java.io.IOException;

public class ConverterRuntimeException extends RuntimeException {
    public ConverterRuntimeException() {
    }

    public ConverterRuntimeException(String message) {
        super(message);
    }

    public ConverterRuntimeException(IOException e) {super(e);}
}
