package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TaskRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate limitDate;

    @NotBlank
    private UUID fkEmployee;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;
}
