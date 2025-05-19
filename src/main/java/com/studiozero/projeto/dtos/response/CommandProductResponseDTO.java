package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.CommandProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandProductResponseDTO {
    private Integer id;
    private Integer fkProduct;
    private Integer fkCommand;
    private Integer productQuantity;
    private Double unitValue;
    private Double totalValue;

    public CommandProductResponseDTO(CommandProduct commandProduct) {
        this.id = id;
        this.fkProduct = fkProduct;
        this.fkCommand = fkCommand;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
        this.totalValue = totalValue;
    }
}
