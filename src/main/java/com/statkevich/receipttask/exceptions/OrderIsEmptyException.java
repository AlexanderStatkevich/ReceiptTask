package com.statkevich.receipttask.exceptions;

public class OrderIsEmptyException extends RuntimeException {
    public OrderIsEmptyException(String message) {
        super(message);
    }
}