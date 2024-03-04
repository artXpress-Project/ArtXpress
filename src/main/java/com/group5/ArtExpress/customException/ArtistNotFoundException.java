package com.group5.ArtExpress.customException;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message)  {
        super(message);
    }
}
