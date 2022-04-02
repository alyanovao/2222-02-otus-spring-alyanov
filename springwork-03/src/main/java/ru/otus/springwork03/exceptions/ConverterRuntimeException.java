package ru.otus.springwork03.exceptions;

import java.io.IOException;

public class ConverterRuntimeException extends CustomRuntimeException {
    public ConverterRuntimeException() {
    }

    public ConverterRuntimeException(String message) {
        super(message);
    }

    public ConverterRuntimeException(IOException e) {super(e);}
}
