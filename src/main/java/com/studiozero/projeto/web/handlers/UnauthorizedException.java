package com.studiozero.projeto.web.handlers;

import com.studiozero.projeto.application.enums.ApiError;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.UNAUTHORIZED;
    }

    @Override
    public String userResponseMessage() {
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
