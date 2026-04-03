package com.capg.exception;

/**
 * Exception thrown when a Group is not found.
 */
public class GroupNotFoundException extends RuntimeException {

    /**
     * Constructs a GroupNotFoundException with a message.
     *
     * @param message error message
     */
    public GroupNotFoundException(String message) {
        super(message);
    }
}