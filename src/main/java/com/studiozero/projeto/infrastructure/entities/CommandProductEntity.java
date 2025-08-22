package com.studiozero.projeto.infrastructure.entities;

public class CommandProductEntity {
    private final Integer id;
    private ProductEntity productEntity;
    private CommandEntity commandEntity;
    private Integer productQuantity;
    private Double unitValue;

    public CommandProductEntity(Integer id, ProductEntity productEntity, CommandEntity commandEntity, Integer productQuantity, Double unitValue) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        validateProduct(productEntity);
        validateCommand(commandEntity);
        validateProductQuantity(productQuantity);
        validateUnitValue(unitValue);
        this.id = id;
        this.productEntity = productEntity;
        this.commandEntity = commandEntity;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
    }

    private void validateProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
    }

    private void validateCommand(CommandEntity commandEntity) {
        if (commandEntity == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
    }

    private void validateProductQuantity(Integer productQuantity) {
        if (productQuantity == null || productQuantity <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than zero");
        }
    }

    private void validateUnitValue(Double unitValue) {
        if (unitValue == null || unitValue <= 0) {
            throw new IllegalArgumentException("Unit value must be greater than zero");
        }
    }

    public Integer getId() {
        return id;
    }

    public ProductEntity getProduct() {
        return productEntity;
    }

    public void changeProduct(ProductEntity newProductEntity) {
        validateProduct(newProductEntity);
        this.productEntity = newProductEntity;
    }

    public CommandEntity getCommand() {
        return commandEntity;
    }

    public void changeCommand(CommandEntity newCommandEntity) {
        validateCommand(newCommandEntity);
        this.commandEntity = newCommandEntity;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void changeProductQuantity(Integer newProductQuantity) {
        validateProductQuantity(newProductQuantity);
        this.productQuantity = newProductQuantity;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void changeUnitValue(Double newUnitValue) {
        validateUnitValue(newUnitValue);
        this.unitValue = newUnitValue;
    }

    public void addQuantity(Integer quantity) {
        changeProductQuantity(this.productQuantity + quantity);
    }
}
