package com.studiozero.projeto.applications.web.controllers.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HttpErrorResponse {
    private final String type;
    private final String message;
    private final Object details;

    public HttpErrorResponse(String type, String message, Object details) {
        this.type = type;
        this.message = message;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetails() {
        return details;
    }
}
