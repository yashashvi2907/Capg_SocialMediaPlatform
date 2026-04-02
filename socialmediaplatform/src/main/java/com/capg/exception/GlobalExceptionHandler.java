package com.capg.exception;

import com.capg.dto.ErrorDTO;
import com.capg.exception.FriendNotFoundException;
import com.capg.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    
 // Friend Not Found
    @ExceptionHandler(FriendNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFriendNotFound1(FriendNotFoundException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Generic Exception (optional but good)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


