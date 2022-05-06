package ru.otus.springwork06.exception;

public class KindBookNotFoundException extends LibraryException {

    public KindBookNotFoundException() {
        super();
    }

    public KindBookNotFoundException(String message) {
        super(message);
    }

    public KindBookNotFoundException(Exception e) {
        super(e);
    }
}
