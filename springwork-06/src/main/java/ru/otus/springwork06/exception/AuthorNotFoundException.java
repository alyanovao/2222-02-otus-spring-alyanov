package ru.otus.springwork06.exception;

public class AuthorNotFoundException extends LibraryException {

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
