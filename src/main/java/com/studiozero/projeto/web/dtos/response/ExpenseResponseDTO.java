package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ExpenseResponseDTO {
    private Integer id;
    private LocalDate date;
    private ExpenseCategory expenseCategory;
    private String description;
    private Double amountSpend;
    private Integer quantity;
    private PaymentType paymentType;
    private Integer fkProduct;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Integer id, LocalDate date, ExpenseCategory expenseCategory, String description, Double amountSpend, Integer quantity, PaymentType paymentType, Integer fkProduct) {
        this.id = id;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.amountSpend = amountSpend;
        this.quantity = quantity;
        this.paymentType = paymentType;
        this.fkProduct = fkProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmountSpend() {
        return amountSpend;
    }

    public void setAmountSpend(Double amountSpend) {
        this.amountSpend = amountSpend;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(Integer fkProduct) {
        this.fkProduct = fkProduct;
    }
}


