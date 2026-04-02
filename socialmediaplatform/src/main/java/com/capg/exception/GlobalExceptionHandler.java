package com.capg.exception;

import com.capg.dto.ErrorDTO;
import com.capg.exception.FriendNotFoundException;
import com.capg.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle Friend Not Found Exception
    @ExceptionHandler(FriendNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleFriendNotFound(FriendNotFoundException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Friend API Error"
        );
    }

    //Handle Generic Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGeneralException(Exception ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Internal Server Error"
        );
    }

    // Handle Message Not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleMessageNotFound(ResourceNotFoundException ex) {
        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Message API Error"
        );
    }

    // Handle Post Not Found Exception
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlePostNotFound(PostNotFoundException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Post API Error"
        );
    }

    // Handle Notification Not Found Exception
    @ExceptionHandler(NotificationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotificationNotFound(NotificationNotFoundException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Notification API Error"
        );
    }

    // Handle Invalid User ID Exception
    @ExceptionHandler(InvalidUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInvalidUserId(InvalidUserIdException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "User API Error"
        );
    }

    // Handle Bad Request Exception
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(BadRequestException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Bad Request"
        );
    }
}


