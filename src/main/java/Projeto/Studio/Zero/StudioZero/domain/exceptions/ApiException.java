package Projeto.Studio.Zero.StudioZero.domain.exceptions;

import Projeto.Studio.Zero.StudioZero.domain.enums.ApiError;

public abstract class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public abstract ApiError apiError();
    public abstract String userResponseMessage();
    public abstract int httpStatus();
}
