package com.studiozero.projeto.controllers.handlers;

import com.studiozero.projeto.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpErrorResponse> handlerApiException(ApiException ex) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(
                ex.apiError().name(),
                ex.userResponseMessage(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.httpStatus()));
    }
}
