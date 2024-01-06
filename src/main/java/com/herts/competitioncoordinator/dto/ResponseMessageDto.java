package com.herts.competitioncoordinator.dto;

import org.springframework.http.HttpStatus;

public class ResponseMessageDto {
    private String message;
    private HttpStatus status;

    public ResponseMessageDto() {
    }
    public ResponseMessageDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
