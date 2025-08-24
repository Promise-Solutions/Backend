package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Product;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double clientValue;
    private Double internalValue;
    private Double totalBuyValue;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.clientValue = product.getClientValue();
        this.internalValue = product.getInternalValue();
        this.totalBuyValue = product.getTotalBuyValue();
    }

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Integer id, String name, Integer quantity, Double clientValue, Double internalValue, Double totalBuyValue) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.clientValue = clientValue;
        this.internalValue = internalValue;
        this.totalBuyValue = totalBuyValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getClientValue() {
        return clientValue;
    }

    public void setClientValue(Double clientValue) {
        this.clientValue = clientValue;
    }

    public Double getInternalValue() {
        return internalValue;
    }

    public void setInternalValue(Double internalValue) {
        this.internalValue = internalValue;
    }

    public Double getTotalBuyValue() {
        return totalBuyValue;
    }

    public void setTotalBuyValue(Double totalBuyValue) {
        this.totalBuyValue = totalBuyValue;
    }
}
