package ru.otus.springwork12.exception;

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
