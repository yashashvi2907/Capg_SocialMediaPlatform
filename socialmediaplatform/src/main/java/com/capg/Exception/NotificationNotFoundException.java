package com.capg.Exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message){
        super(message);
    }
}
