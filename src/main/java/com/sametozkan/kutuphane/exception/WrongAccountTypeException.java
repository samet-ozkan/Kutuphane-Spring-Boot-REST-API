package com.sametozkan.kutuphane.exception;

public class WrongAccountTypeException extends Exception {

    public WrongAccountTypeException() {
        super("Hesap türü uyumsuz.");
    }

    public WrongAccountTypeException(String message) {
        super(message);
    }

    public WrongAccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

