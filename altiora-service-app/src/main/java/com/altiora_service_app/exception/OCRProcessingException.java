package com.altiora_service_app.exception;

public class OCRProcessingException extends RuntimeException {
    public OCRProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}