package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductRequestDTO {

    @NotBlank(message = "name value is mandatory")
    private String name;

    @NotNull(message = "qtdProduct value is mandatory")
    @Positive(message = "qtdProduct must be a positive number")
    private Integer quantity;

    @NotNull(message = "unitValue value is mandatory")
    @Positive
    private Double unitValue;
}
