package com.sampleClass.BlogApp.exceptions;

public class NullPostObjectException extends Throwable {
    public NullPostObjectException() {
    }

    public NullPostObjectException(String message) {
        super(message);
    }

    public NullPostObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
