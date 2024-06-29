package com.sametozkan.kutuphane.exception;

public class InvalidVerificationCodeException extends Exception {

    public InvalidVerificationCodeException() {
        super("Doğrulama kodu geçersiz.");
    }

    public InvalidVerificationCodeException(String message) {
        super(message);
    }

    public InvalidVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
