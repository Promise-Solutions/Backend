package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandRequestDTO {

    @NotNull(message = "Command Number is mandatory")
    private Integer commandNumber;

    @NotNull(message = "Opening date and time is mandatory")
    private LocalDateTime openingDateTime;

    private LocalDateTime closingDateTime;

    private Double discount;

    @NotNull(message = "Total value is mandatory")
    private Double totalValue;

    @Nullable
    private UUID fkClient;

    @NotNull(message = "Employee ID is mandatory")
    private UUID fkEmployee;

    @NotNull(message = "Status is mandatory")
    private Status status;
}
