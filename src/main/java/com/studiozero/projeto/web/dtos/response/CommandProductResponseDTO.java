package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.CommandProduct;
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
        if (commandProduct == null) {
            return;
        }
        this.id = commandProduct.getId();
        this.fkProduct = commandProduct.getProduct() != null ? commandProduct.getProduct().getId() : null;
        this.fkCommand = commandProduct.getCommand() != null ? commandProduct.getCommand().getId() : null;
        this.productQuantity = commandProduct.getProductQuantity();
        this.unitValue = commandProduct.getUnitValue();
        this.totalValue = (commandProduct.getUnitValue() != null && commandProduct.getProductQuantity() != null)
                ? commandProduct.getUnitValue() * commandProduct.getProductQuantity()
                : null;
    }
}
