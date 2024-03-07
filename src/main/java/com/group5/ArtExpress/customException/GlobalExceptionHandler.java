package com.group5.ArtExpress.customException;

import com.group5.ArtExpress.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CouldNotFindEmailException.class)
    public Map<String,String> handleUserException(CouldNotFindEmailException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message is ", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public Map<String, String> emailAlreadyExistException(EmailAlreadyExistException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message is ", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(NoListOfEnabledAttendeesFound.class)
    public Map<String,String> noListOfEnabledAttendeesException(NoListOfEnabledAttendeesFound noEnabledAttendees){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("error message is ", noEnabledAttendees.getMessage());
        return errorMap;

    }
    @ExceptionHandler(TokenWasNotFoundException.class)
    public Map<String, String> tokenWasNotFoundException(TokenWasNotFoundException exception){
        Map<String,String> erroMap = new HashMap<>();
        erroMap.put("error message is ",exception.getMessage());
        return erroMap;

    }
    @ExceptionHandler(CommentException.class)
    public ResponseEntity<HttpResponse> commentException(CommentException exception){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Comment Exception", exception.getMessage()))
                        .message("Bad request")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<HttpResponse> idNotFoundException(IdNotFoundException exception){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Id Exception", exception.getMessage()))
                        .message("Does not exist")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }

    @ExceptionHandler(ArtworkNotFoundException.class)
    public ResponseEntity<HttpResponse> artWorkFoundException(ArtworkNotFoundException exception){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Artwork Exception", exception.getMessage()))
                        .message("Does not exist")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }

    @ExceptionHandler(NoListOfArtworkWithThatParticularGenre.class)
    public ResponseEntity<HttpResponse> artWorkWithThatGenreFoundException(NoListOfArtworkWithThatParticularGenre exception){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Artwork Exception", exception.getMessage()))
                        .message("Does not exist")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }




}
