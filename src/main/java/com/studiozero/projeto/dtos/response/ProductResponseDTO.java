package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double unitValue;
    private Double buyValue;

    public ProductResponseDTO(Product product) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitValue = unitValue;
        this.buyValue = buyValue;
    }
}
