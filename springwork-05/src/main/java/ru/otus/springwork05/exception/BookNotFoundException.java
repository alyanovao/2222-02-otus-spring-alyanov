package ru.otus.springwork05.exception;

public class BookNotFoundException extends LibraryException {

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
