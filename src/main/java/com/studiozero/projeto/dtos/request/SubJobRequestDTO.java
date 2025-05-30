package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobRequestDTO {

    @NotBlank(message = "Title value is mandatory")
    private String title;

    @NotBlank(message = "Description value is mandatory")
    private String description;

    @NotNull(message = "Value is mandatory")
    @Positive
    private Double value;

    private LocalDate date;

    private LocalTime startTime;

    @NotNull(message = "Needs Room value is mandatory")
    private Boolean needsRoom;

    private LocalTime expectedEndTime;

    @NotNull(message = "Status value is mandatory")
    private Status status;

    @NotNull(message = "fkService value is mandatory")
    private UUID fkService;
}
