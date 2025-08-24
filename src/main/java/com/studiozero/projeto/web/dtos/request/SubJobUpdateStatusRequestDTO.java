package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobUpdateStatusRequestDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public SubJobUpdateStatusRequestDTO() {
    }

    public SubJobUpdateStatusRequestDTO(Status status) {
        this.status = status;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }
}
