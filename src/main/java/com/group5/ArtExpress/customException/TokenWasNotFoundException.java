package com.group5.ArtExpress.customException;

public class TokenWasNotFoundException extends RuntimeException{
    public TokenWasNotFoundException(String message){
        super(message);
    }
}
