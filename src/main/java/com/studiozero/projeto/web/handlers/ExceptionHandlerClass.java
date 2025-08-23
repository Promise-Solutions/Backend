package com.studiozero.projeto.web.handlers;

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(
                "BAD_REQUEST",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<HttpErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
