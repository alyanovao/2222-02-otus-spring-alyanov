package ru.otus.springwork04.exceptions;

import java.io.IOException;

public class NotFilledStudentException extends CustomRuntimeException {
    public NotFilledStudentException() {
        super();
    }

    public NotFilledStudentException(String message) {
        super(message);
    }

    public NotFilledStudentException(IOException e) {
        super(e);
    }
}
