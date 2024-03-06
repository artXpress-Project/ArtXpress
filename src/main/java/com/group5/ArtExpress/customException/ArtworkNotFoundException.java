package com.group5.ArtExpress.customException;

public class ArtworkNotFoundException extends RuntimeException{
    public ArtworkNotFoundException(String message) {
        super(message);
    }
}
