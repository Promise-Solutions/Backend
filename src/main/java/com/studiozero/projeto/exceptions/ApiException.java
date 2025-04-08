package com.studiozero.projeto.exceptions;

import com.studiozero.projeto.enums.ApiError;

public abstract class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public abstract ApiError apiError();
    public abstract String userResponseMessage();
    public abstract int httpStatus();
}
