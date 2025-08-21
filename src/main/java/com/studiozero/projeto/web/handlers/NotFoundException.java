package com.studiozero.projeto.web.handlers;

import com.studiozero.projeto.application.enums.ApiError;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.NOT_FOUND;
    }

    @Override
    public String userResponseMessage() {
        return "The requested entity could not be found: " + getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}
