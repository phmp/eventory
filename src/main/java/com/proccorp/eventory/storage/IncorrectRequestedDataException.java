package com.proccorp.eventory.storage;

public class IncorrectRequestedDataException extends RuntimeException {
    public IncorrectRequestedDataException(String message) {
        super(message);
    }
}
