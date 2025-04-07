package com.studiozero.projeto.domain.exceptions;

import com.studiozero.projeto.domain.enums.ApiError;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExists extends ApiException {
    public EntityAlreadyExists(String message) {
        super(message);
    }

    @Override
    public ApiError apiError() {
        return ApiError.NO_CONTENT;
    }

    @Override
    public String userResponseMessage() {
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.NO_CONTENT.value();
    }
}
