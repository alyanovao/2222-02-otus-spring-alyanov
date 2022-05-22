package ru.otus.springwork06.exception;

public class ComponentException extends ApplicationException {

    public ComponentException() {
        super();
    }

    public ComponentException(Exception e) {
        super(e);
    }
}
