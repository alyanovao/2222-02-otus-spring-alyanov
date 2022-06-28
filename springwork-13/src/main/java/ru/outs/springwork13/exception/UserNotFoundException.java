package ru.outs.springwork13.exception;

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
