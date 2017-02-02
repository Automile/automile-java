package com.automile;

public class AutomileException extends RuntimeException {

    public AutomileException(Throwable cause) {
        super(cause);
    }

    public AutomileException(String message) {
        super(message);
    }
}
