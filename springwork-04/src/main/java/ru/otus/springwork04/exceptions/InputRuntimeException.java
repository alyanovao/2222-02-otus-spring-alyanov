package ru.otus.springwork04.exceptions;

import java.io.IOException;

public class InputRuntimeException extends CustomRuntimeException {
    public InputRuntimeException() {
    }

    public InputRuntimeException(String message) {
        super(message);
    }

    public InputRuntimeException(IOException e) {super(e);}
}
