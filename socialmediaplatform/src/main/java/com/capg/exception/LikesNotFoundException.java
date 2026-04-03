package com.capg.exception;

/**
 * Exception thrown when Likes are not found.
 */
public class LikesNotFoundException extends RuntimeException {

    /**
     * Constructs a LikesNotFoundException with a message.
     *
     * @param message error message
     */
    public LikesNotFoundException(String message) {
        super(message);
    }
}