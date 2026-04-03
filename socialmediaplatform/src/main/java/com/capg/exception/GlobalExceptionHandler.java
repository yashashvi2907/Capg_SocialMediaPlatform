package com.capg.exception;

import com.capg.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
    
 // Friend Not Found
    @ExceptionHandler(FriendNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFriendNotFound1(FriendNotFoundException ex) {

    // Handle Bad Request Exception
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(BadRequestException ex) {

        return new ErrorDTO(
                ex.getMessage(),
                LocalDate.now(),
                "Bad Request"
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostException(PostNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoData(NoDataFoundException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 🔴 Comment Exception
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentException(CommentNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
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


