package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandProductRequestDTO {

    @NotNull(message = "fkProduct value is mandatory")
    private Integer fkProduct;

    @NotNull(message = "fkCommand value is mandatory")
    private Integer fkCommand;

    @NotNull(message = "productQuantity value is mandatory")
    @Positive
    private Integer productQuantity;


    // não vai ser solicitado, isInternal que vai determinar isso
    @NotNull(message = "unitValue is mandatory")
    @Positive
    private Double unitValue;

}
