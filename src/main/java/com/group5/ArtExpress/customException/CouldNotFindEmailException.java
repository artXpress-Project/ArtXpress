package com.group5.ArtExpress.customException;

public class CouldNotFindEmailException extends RuntimeException{
    public CouldNotFindEmailException(String message){
        super(message);
    }
}
