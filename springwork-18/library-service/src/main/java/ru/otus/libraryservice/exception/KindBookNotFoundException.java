package ru.otus.libraryservice.exception;

public class KindBookNotFoundException extends ApplicationException {

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
