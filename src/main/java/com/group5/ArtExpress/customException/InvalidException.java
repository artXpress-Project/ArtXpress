package com.group5.ArtExpress.customException;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
