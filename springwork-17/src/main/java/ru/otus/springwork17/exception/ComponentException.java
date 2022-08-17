package ru.otus.springwork17.exception;

public class ComponentException extends ApplicationException {

    public ComponentException() {
        super();
    }

    public ComponentException(Exception e) {
        super(e);
    }
}
