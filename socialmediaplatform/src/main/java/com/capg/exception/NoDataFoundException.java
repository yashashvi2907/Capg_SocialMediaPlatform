package com.capg.exception;

/**
 * NoDataFoundException
 * 
 * This is a custom exception class used to handle scenarios
 * where no data is found in the system for a valid request.
 * 
 * It extends RuntimeException, so it is an unchecked exception.
 * 
 * Usage:
 * - Thrown when a query returns an empty result
 * - Example: No pending requests or no accepted friends found
 * - Helps provide meaningful response to the client instead of empty list
 */
public class NoDataFoundException extends RuntimeException {

    /**
     * Constructor with custom error message
     * 
     * @param message detailed error message
     */
    public NoDataFoundException(String message) {
        super(message);
    }
}