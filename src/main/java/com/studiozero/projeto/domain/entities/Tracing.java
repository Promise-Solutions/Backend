package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.Context;
import java.time.LocalDateTime;

/**
 * Entidade Tracing (Rastreio) - Clean Architecture, Java puro, SOLID.
 */
public class Tracing {
    private final Integer id;
    private Context context;
    private LocalDateTime dateTime;

    public Tracing(Integer id, Context context, LocalDateTime dateTime) {
        validateContext(context);
        validateDateTime(dateTime);
        this.id = id;
        this.context = context;
        this.dateTime = dateTime;
    }

    public Tracing() {
        this.id = null;
    }

    private void validateContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
    }

    private void validateDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("DateTime cannot be null");
        }
    }

    public Integer getId() {
        return id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        validateContext(context);
        this.context = context;
    }

    public void changeContext(Context newContext) {
        validateContext(newContext);
        this.context = newContext;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        validateDateTime(dateTime);
        this.dateTime = dateTime;
    }

    public void changeDateTime(LocalDateTime newDateTime) {
        validateDateTime(newDateTime);
        this.dateTime = newDateTime;
    }
}
