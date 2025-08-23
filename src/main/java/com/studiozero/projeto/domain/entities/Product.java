package com.studiozero.projeto.domain.entities;

public class Product {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double clientValue;
    private Double internalValue;
    private Double totalBuyValue;

    public Product(Integer id, String name, Integer quantity, Double clientValue, Double internalValue) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.clientValue = clientValue;
        this.internalValue = internalValue;
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

}