package com.group5.ArtExpress.customException;

public class NotFoundExceptions extends RuntimeException{
    public NotFoundExceptions(String message){
        super(message);
    }
}
