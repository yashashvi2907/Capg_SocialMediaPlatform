package com.capg.dto;

import java.time.LocalDate;

public class ErrorDTO {

    private String message;
    private LocalDate date;
    private String details;

    public ErrorDTO() {}

    public ErrorDTO(String message, LocalDate date, String details) {
        this.message = message;
        this.date = date;
        this.details = details;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}