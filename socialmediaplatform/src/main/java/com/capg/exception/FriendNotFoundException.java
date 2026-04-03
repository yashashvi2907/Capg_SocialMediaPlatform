package com.capg.exception;

/**
 * FriendNotFoundException
 * 
 * This is a custom exception class used to handle scenarios
 * where a requested Friend record is not found in the system.
 * 
 * It extends RuntimeException, so it is an unchecked exception.
 * 
 * Usage:
 * - Thrown when a friendship ID does not exist
 * - Helps provide meaningful error messages to the client
 */

public class FriendNotFoundException extends RuntimeException {

    /**
     * Constructor with custom error message
     * 
     * @param message detailed error message
     */
    public FriendNotFoundException(String message) {
        super(message);
    }
}