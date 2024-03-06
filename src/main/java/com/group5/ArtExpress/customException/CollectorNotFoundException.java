package com.group5.ArtExpress.customException;

public class CollectorNotFoundException extends RuntimeException {
    public CollectorNotFoundException(String message) {
        super(message);
    }
}
