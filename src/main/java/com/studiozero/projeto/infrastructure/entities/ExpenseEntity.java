package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;

import java.time.LocalDate;

public class ExpenseEntity {
    private Integer id;
    private LocalDate date;
    private ExpenseCategory expenseCategory;
    private String description;
    private Integer quantity;
    private Double amountSpend;
    private ProductEntity productEntity;
    private PaymentType paymentType;

    public ExpenseEntity(Integer id, LocalDate date, ExpenseCategory expenseCategory, String description, Integer quantity,
                         Double amountSpend, ProductEntity productEntity, PaymentType paymentType) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        validateDescription(description);
        validateAmountSpend(amountSpend);
        this.id = id;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.quantity = quantity;
        this.amountSpend = amountSpend;
        this.productEntity = productEntity;
        this.paymentType = paymentType;
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (description.length() < 2 || description.length() > 200) {
            throw new IllegalArgumentException("Description must be between 2 and 200 characters");
        }
    }

    private void validateAmountSpend(Double amountSpend) {
        if (amountSpend == null || amountSpend <= 0) {
            throw new IllegalArgumentException("Amount spend must be greater than zero");
        }
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAmountSpend() {
        return amountSpend;
    }

    public ProductEntity getProduct() {
        return productEntity;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void changeDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }

    public void changeAmountSpend(Double newAmountSpend) {
        validateAmountSpend(newAmountSpend);
        this.amountSpend = newAmountSpend;
    }
}
