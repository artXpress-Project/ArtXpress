package com.group5.ArtExpress.customException;

public class ActionForbiddenAttempt extends RuntimeException {
    public ActionForbiddenAttempt(String message) {
        super(message);
    }
}
