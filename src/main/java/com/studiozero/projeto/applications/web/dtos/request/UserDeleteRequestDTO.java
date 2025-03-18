package com.studiozero.projeto.applications.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class UserDeleteRequestDTO {
    @NotBlank
    private UUID id;

    public @NotBlank UUID getId() {
        return id;
    }

    public void setId(@NotBlank UUID id) {
        this.id = id;
    }
}
