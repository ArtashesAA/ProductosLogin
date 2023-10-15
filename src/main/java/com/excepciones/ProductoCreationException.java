package com.excepciones;

public class ProductoCreationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductoCreationException(String message) {
        super(message);
    }

    public ProductoCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}