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
    @NotBlank(message = "Title value is mandatory")
    private String title;

    @NotBlank(message = "Description value is mandatory")
    private String description;

    @NotBlank(message = "Start date value is mandatory")
    private LocalDate startDate;

    @NotBlank(message = "Limit date value is mandatory")
    private LocalDate limitDate;

    private UUID fkEmployee;

    @NotBlank(message = "Status value is mandatory")
    @Enumerated(EnumType.STRING)
    private Status status;
}
