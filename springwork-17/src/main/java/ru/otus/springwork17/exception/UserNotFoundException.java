package ru.otus.springwork17.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Exception e) {
        super(e);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
