package com.capg.exception;

public class LikesNotFoundException extends RuntimeException {

    public LikesNotFoundException(String message) {
        super(message);
    }
}