package com.capg.Exception;

public class InvalidUserIdException extends RuntimeException{
    public InvalidUserIdException(String message){
        super(message);
    }
}
