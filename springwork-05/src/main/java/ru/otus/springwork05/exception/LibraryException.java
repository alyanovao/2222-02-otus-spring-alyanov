package ru.otus.springwork05.exception;

public class LibraryException extends RuntimeException {

    public LibraryException() {
        super();
    }

    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(Exception e) {
        super(e);
    }
}