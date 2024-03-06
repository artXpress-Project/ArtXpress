package com.group5.ArtExpress.customException;

public class ArtistNotEnabled extends RuntimeException {
    public ArtistNotEnabled(String message) {
        super(message);
    }
}
