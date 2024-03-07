package com.group5.ArtExpress.customException;

public class NoListOfArtworkWithThatParticularGenre extends RuntimeException{
    public NoListOfArtworkWithThatParticularGenre(String message){
        super(message);
    }
}
