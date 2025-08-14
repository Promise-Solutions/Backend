package com.studiozero.projeto.application.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class JobRequestDTO {

    @NotNull(message = "fkClient value is mandatory")
    private UUID fkClient;

    @NotBlank
    private String title;

    @NotNull(message = "Total value value is mandatory")
    @PositiveOrZero
    private Double totalValue;

    @NotNull(message = "Job Category value is mandatory")
    private JobCategory category;

    @NotNull(message = "Status value is mandatory")
    private Status status;

    @NotNull(message = "Job Type value is mandatory")
    private JobType serviceType;
}