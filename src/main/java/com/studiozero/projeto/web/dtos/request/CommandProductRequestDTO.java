package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    public CommandProductRequestDTO() {
    }

    public CommandProductRequestDTO(Integer fkProduct, Integer fkCommand, Integer productQuantity, Double unitValue) {
        this.fkProduct = fkProduct;
        this.fkCommand = fkCommand;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
    }

    public @NotNull(message = "fkProduct value is mandatory") Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(@NotNull(message = "fkProduct value is mandatory") Integer fkProduct) {
        this.fkProduct = fkProduct;
    }

    public @NotNull(message = "fkCommand value is mandatory") Integer getFkCommand() {
        return fkCommand;
    }

    public void setFkCommand(@NotNull(message = "fkCommand value is mandatory") Integer fkCommand) {
        this.fkCommand = fkCommand;
    }

    public @NotNull(message = "productQuantity value is mandatory") @Positive Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@NotNull(message = "productQuantity value is mandatory") @Positive Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public @NotNull(message = "unitValue is mandatory") @Positive Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(@NotNull(message = "unitValue is mandatory") @Positive Double unitValue) {
        this.unitValue = unitValue;
    }
}
