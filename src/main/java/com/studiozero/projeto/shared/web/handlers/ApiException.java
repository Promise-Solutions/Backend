package com.studiozero.projeto.shared.web.handlers;

import com.studiozero.projeto.shared.application.enums.ApiError;

public abstract class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public abstract ApiError apiError();
    public abstract String userResponseMessage();
    public abstract int httpStatus();
}
