package ru.outs.springwork13.exception;

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
