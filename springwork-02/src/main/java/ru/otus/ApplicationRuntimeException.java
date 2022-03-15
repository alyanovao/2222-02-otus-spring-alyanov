package ru.otus;

import java.io.IOException;

public class ApplicationRuntimeException extends RuntimeException {
    public ApplicationRuntimeException(IOException e) {
        super(e);
    }
}
