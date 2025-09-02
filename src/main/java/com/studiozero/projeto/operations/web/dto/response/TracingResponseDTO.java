package com.studiozero.projeto.operations.web.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.operations.domain.entities.Tracing;
import com.studiozero.projeto.shared.application.enums.Context;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TracingResponseDTO {
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Context context;

    private LocalDateTime dateTime;

    public TracingResponseDTO(Tracing tracing) {
        this.id = id;
        this.context = context;
        this.dateTime= dateTime;
    }

    public TracingResponseDTO() {
    }

    public TracingResponseDTO(Integer id, Context context, LocalDateTime dateTime) {
        this.id = id;
        this.context = context;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
