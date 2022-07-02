package ru.otus.springwork12.exception;

public class BookNotFoundException extends ApplicationException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Exception e) {
        super(e);
    }
}
