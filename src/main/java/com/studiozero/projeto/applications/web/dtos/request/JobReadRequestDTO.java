package com.studiozero.projeto.applications.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class JobReadRequestDTO {
    @NotBlank
    private Integer id;

    public @NotBlank Integer getId() {
        return id;
    }

    public void setId(@NotBlank Integer id) {
        this.id = id;
    }
}
