package ru.otus.springwork16.exception;

public class
ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }

    public ApplicationException(Exception e) {
        super(e);
    }

    public ApplicationException(String message) {
        super(message);
    }
}
