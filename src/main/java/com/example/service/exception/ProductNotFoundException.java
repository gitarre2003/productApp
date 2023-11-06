package com.example.service.exception;


public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String customMessage) {
        super(customMessage);
    }
}





