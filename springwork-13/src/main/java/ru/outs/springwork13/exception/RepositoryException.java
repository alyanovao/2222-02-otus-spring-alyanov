package ru.outs.springwork13.exception;

public class RepositoryException extends ApplicationException {

    public RepositoryException() {
        super();
    }

    public RepositoryException(Exception e) {
        super(e);
    }
}
