package com.studiozero.projeto.web.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "INVALID_DATA");
        body.put("message", "Invalid request parameters.");

        List<String> invalidFields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .distinct()
                .collect(Collectors.toList());

        body.put("invalidFields", invalidFields);

        Map<String, List<String>> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        LinkedHashMap::new,
                        Collectors.mapping(error -> {
                            String defaultMessage = error.getDefaultMessage();
                            Object rejectedValue = error.getRejectedValue();
                            return String.format("Message: %s. Informed value: %s", defaultMessage, rejectedValue);
                        }, Collectors.toList())
                ));

        body.put("details", details);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}