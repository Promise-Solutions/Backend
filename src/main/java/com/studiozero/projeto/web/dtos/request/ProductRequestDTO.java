package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductRequestDTO {

    @NotBlank(message = "name value is mandatory")
    private String name;

    @NotNull(message = "qtdProduct value is mandatory")
    @PositiveOrZero(message = "qtdProduct must be a positive number")
    private Integer quantity;

    @NotNull(message = "clientValue  is mandatory")
    @PositiveOrZero(message = "clientValue must not be negative")
    private Double clientValue;

    @NotNull(message = "internalValue  is mandatory")
    @PositiveOrZero(message = "internalValue must not be negative")
    private Double internalValue;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Integer quantity, Double clientValue, Double internalValue) {
        this.name = name;
        this.quantity = quantity;
        this.clientValue = clientValue;
        this.internalValue = internalValue;
    }

    public @NotBlank(message = "name value is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name value is mandatory") String name) {
        this.name = name;
    }

    public @NotNull(message = "qtdProduct value is mandatory") @PositiveOrZero(message = "qtdProduct must be a positive number") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "qtdProduct value is mandatory") @PositiveOrZero(message = "qtdProduct must be a positive number") Integer quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "clientValue  is mandatory") @PositiveOrZero Double getClientValue() {
        return clientValue;
    }

    public void setClientValue(@NotNull(message = "clientValue  is mandatory") @PositiveOrZero Double clientValue) {
        this.clientValue = clientValue;
    }

    public void setInternalValue(@NotNull(message = "internalValue  is mandatory") @PositiveOrZero Double internalValue) {
        this.internalValue = internalValue;
    }

    public @NotNull(message = "internalValue  is mandatory") @PositiveOrZero Double getInternalValue() {
        return internalValue;
    }
}
