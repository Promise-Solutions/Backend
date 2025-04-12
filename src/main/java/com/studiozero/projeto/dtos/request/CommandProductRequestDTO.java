package com.studiozero.projeto.dtos.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandProductRequestDTO {

    @NotBlank(message = "fkProduct value is mandatory")
    private Integer fkProduct;

    @NotBlank(message = "fkCommand value is mandatory")
    private Integer fkCommand;

    @Positive
    @NotBlank(message = "productQuantity value is mandatory")
    private Integer productQuantity;

    @Positive
    @NotBlank(message = "unitValue is mandatory")
    private Double unitValue;
}
