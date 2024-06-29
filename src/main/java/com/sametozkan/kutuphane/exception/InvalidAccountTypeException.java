package com.sametozkan.kutuphane.exception;

public class InvalidAccountTypeException extends Exception {

    public InvalidAccountTypeException() {
        super("Hesap türü uyumsuz.");
    }

    public InvalidAccountTypeException(String message) {
        super(message);
    }

    public InvalidAccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

