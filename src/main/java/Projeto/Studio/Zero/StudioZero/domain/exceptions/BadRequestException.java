package Projeto.Studio.Zero.StudioZero.domain.exceptions;

import Projeto.Studio.Zero.StudioZero.domain.enums.ApiError;
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
        return getMessage();
    }

    @Override
    public int httpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
