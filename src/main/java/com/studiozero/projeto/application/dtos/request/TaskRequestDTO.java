package com.studiozero.projeto.application.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Title value is mandatory")
    private String title;

    @NotBlank(message = "Description value is mandatory")
    private String description;

    @NotNull(message = "Start date value is mandatory")
    private LocalDate startDate;

    private LocalDate limitDate;

    @Nullable
    private UUID fkEmployee;

    private UUID fkAssigned;

    @NotNull(message = "Status value is mandatory")
    @Enumerated(EnumType.STRING)
    private Status status;
}
