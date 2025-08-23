package com.studiozero.projeto.web.handlers;

import com.studiozero.projeto.application.enums.ApiError;
import org.springframework.http.HttpStatus;

public class DeleteOwnUserException extends ApiException {
    public DeleteOwnUserException(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.PRECONDITION_REQUIRED;
    }

    @Override
    public String userResponseMessage() {
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.PRECONDITION_REQUIRED.value();
    }
}
