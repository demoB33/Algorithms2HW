package org.example;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException() {
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}