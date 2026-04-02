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
}


