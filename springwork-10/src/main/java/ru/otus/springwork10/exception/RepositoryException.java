package ru.otus.springwork10.exception;

public class RepositoryException extends ApplicationException {

    public RepositoryException() {
        super();
    }

    public RepositoryException(Exception e) {
        super(e);
    }
}
