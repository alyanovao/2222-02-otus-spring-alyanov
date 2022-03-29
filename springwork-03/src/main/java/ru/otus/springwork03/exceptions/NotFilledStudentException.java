package ru.otus.springwork03.exceptions;

import java.io.IOException;

public class NotFilledStudentException extends RuntimeException {
    public NotFilledStudentException() {
    }

    public NotFilledStudentException(String message) {
        super(message);
    }

    public NotFilledStudentException(IOException e) {
        super(e);
    }
}
