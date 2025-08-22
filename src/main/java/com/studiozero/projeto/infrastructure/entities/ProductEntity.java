package com.studiozero.projeto.infrastructure.entities;

public class ProductEntity {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double clientValue;
    private Double internalValue;
    private Double totalBuyValue;

    public ProductEntity(Integer id, String name, Integer quantity, Double clientValue, Double internalValue,
                         Double totalBuyValue) {
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

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getClientValue() {
        return clientValue;
    }

    public Double getInternalValue() {
        return internalValue;
    }

    public Double getTotalBuyValue() {
        return totalBuyValue;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setClientValue(Double clientValue) {
        this.clientValue = clientValue;
    }

    public void setInternalValue(Double internalValue) {
        this.internalValue = internalValue;
    }

    public void setTotalBuyValue(Double totalBuyValue) {
        this.totalBuyValue = totalBuyValue;
    }
}