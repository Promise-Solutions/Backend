package com.studiozero.projeto.application.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double clientValue;
    private Double internalValue;


    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.clientValue = product.getClientValue();
        this.internalValue = product.getInternalValue();

    }
}
