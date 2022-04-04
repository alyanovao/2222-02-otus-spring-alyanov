package ru.otus.springwork04.exceptions;

import java.io.IOException;

public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(IOException e) {
        super(e);
    }
}
