package ru.outs.springwork13.exception;

public class AuthorNotFoundException extends ApplicationException {

    public AuthorNotFoundException() {
        super();
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(Exception e) {
        super(e);
    }
}
