package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "name value is mandatory")
    private String name;

    @NotBlank(message = "qtdProduct value is mandatory")
    private Integer quantity;

    @NotBlank(message = "unitValue value is mandatory")
    @Positive
    private Double unitValue;
}
