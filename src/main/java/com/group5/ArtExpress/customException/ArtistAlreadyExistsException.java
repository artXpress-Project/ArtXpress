package com.group5.ArtExpress.customException;

public class ArtistAlreadyExistsException extends RuntimeException {
    public ArtistAlreadyExistsException(String message) {
        super(message);
    }
}
