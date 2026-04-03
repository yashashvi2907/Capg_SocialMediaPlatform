package com.capg.exception;

/**
 * UnauthorizedException
 * 
 * This is a custom exception class used to handle unauthorized access
 * in the application, especially for secured APIs.
 * 
 * It is mainly used in JWT authentication when:
 * - Token is missing
 * - Token is invalid
 * - Token is expired
 * 
 * It extends RuntimeException, so it is an unchecked exception.
 * 
 * Usage:
 * - Thrown when user tries to access secured endpoints without proper authentication
 * - Helps return proper HTTP status (401 Unauthorized)
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Default constructor with predefined message
     */
    public UnauthorizedException() {
        super("Unauthorized access");
    }

    /**
     * Constructor with custom message
     * 
     * @param message detailed error message
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}