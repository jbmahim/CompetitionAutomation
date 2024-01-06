package com.herts.competitioncoordinator.exception;

import com.herts.competitioncoordinator.dto.ResponseMessageDto;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus status;
    public CustomException(ResponseMessageDto responseMessageDto, HttpStatus status) {
        super(responseMessageDto.getMessage());
        this.status = status;
    }

    public CustomException(String s) {
        super(s);
    }

    public HttpStatus getStatus() {
        return status;
    }
}