package com.group5.ArtExpress.customException;

public class GenreDoesNotExistException extends  RuntimeException{
    public GenreDoesNotExistException(String message){
        super(message);
    }
}
