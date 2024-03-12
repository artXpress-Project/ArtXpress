package com.group5.ArtExpress.customException;

public class NotEnabledException extends RuntimeException{
    public NotEnabledException(String message){
        super(message);
    }
}
