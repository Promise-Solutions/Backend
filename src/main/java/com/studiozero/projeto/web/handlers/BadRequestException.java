package com.studiozero.projeto.web.handlers;

import com.studiozero.projeto.application.enums.ApiError;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.BAD_REQUEST;
    }

    @Override
    public String userResponseMessage() {
        return "Invalid request: " + getMessage(); // Updated for more descriptive message
    }

    @Override
    public int httpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
