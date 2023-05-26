package com.example.ECommerce.Application.Exception;

public class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}
