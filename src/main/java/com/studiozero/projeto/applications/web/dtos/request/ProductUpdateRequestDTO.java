package com.studiozero.projeto.applications.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductUpdateRequestDTO {
    @NotNull(message = "Id value is mandatory")
    private Integer id;

    @NotBlank(message = "name value is mandatory")
    private String name;

    @NotBlank(message = "quantity value is mandatory")
    private Integer quantity;

    @NotBlank(message = "unitValue value is mandatory")
    @Positive
    private Double unitValue;
}
