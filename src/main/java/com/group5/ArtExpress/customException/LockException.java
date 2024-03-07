package com.group5.ArtExpress.customException;

public class LockException extends RuntimeException{
    public LockException(String message){
        super(message);
    }
}
