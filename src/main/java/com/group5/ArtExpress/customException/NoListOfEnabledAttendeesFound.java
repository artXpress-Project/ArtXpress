package com.group5.ArtExpress.customException;

public class NoListOfEnabledAttendeesFound extends RuntimeException{
    public NoListOfEnabledAttendeesFound(String message){
        super(message);
    }
}
