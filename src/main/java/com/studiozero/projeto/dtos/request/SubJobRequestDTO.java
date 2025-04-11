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
import java.time.LocalDateTime;
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

    @NotBlank(message = "Value is mandatory")
    @Positive
    private Double value;

    @NotNull(message = "Date value is mandatory")
    private LocalDate date;

    @NotNull(message = "Start Time value is mandatory")
    private LocalDateTime startTime;

    @NotNull(message = "End Time value is mandatory")
    private LocalDateTime endTime;

    @NotNull(message = "Status value is mandatory")
    private Status status;

    @NotBlank(message = "fkService value is mandatory")
    private UUID fkService;
}
