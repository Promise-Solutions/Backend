package com.studiozero.projeto.domain.exceptions;

import com.studiozero.projeto.domain.enums.ApiError;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException{

    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.NOT_FOUND;
    }

    @Override
    public String userResponseMessage() {
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}
