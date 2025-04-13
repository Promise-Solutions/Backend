package com.studiozero.projeto.exceptions;

import com.studiozero.projeto.enums.ApiError;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExists extends ApiException {
    public EntityAlreadyExists(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.CONFLICT;
    }

    @Override
    public String userResponseMessage() {
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.CONFLICT.value();
    }
}
