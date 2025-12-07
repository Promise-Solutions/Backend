package com.studiozero.projeto.domain.entities;

public class CommandProduct {
    private Integer id;
    private Product product;
    private Command command;
    private Integer productQuantity;
    private Double unitValue;

    public CommandProduct(Integer id, Product product, Command command, Integer productQuantity, Double unitValue) {
        validateProduct(product);
        validateCommand(command);
        validateProductQuantity(productQuantity);
        validateUnitValue(unitValue);
        this.id = id;
        this.product = product;
        this.command = command;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
    }

    public CommandProduct() {

    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
    }

    private void validateCommand(Command command) {
        if (command == null) {
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

    public Product getProduct() {
        return product;
    }

    public void changeProduct(Product newProduct) {
        validateProduct(newProduct);
        this.product = newProduct;
    }

    public Command getCommand() {
        return command;
    }

    public void changeCommand(Command newCommand) {
        validateCommand(newCommand);
        this.command = newCommand;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }
}
