package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.CommandProduct;

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

    public CommandProductResponseDTO() {
    }

    public CommandProductResponseDTO(Integer id, Integer fkProduct, Integer fkCommand, Integer productQuantity, Double unitValue, Double totalValue) {
        this.id = id;
        this.fkProduct = fkProduct;
        this.fkCommand = fkCommand;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
        this.totalValue = totalValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(Integer fkProduct) {
        this.fkProduct = fkProduct;
    }

    public Integer getFkCommand() {
        return fkCommand;
    }

    public void setFkCommand(Integer fkCommand) {
        this.fkCommand = fkCommand;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
