package com.studiozero.projeto.web.dtos.request;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ExpenseRequestDTO {

    //mostramos a data do dia na tela, mas possiblitamos que seja alterada
    @NotNull(message = "date must not be null")
    private LocalDate date;

    @NotNull(message = "expenseCategory must not be null")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull(message = "amountSpend is mandatory")
    private Double amountSpend;

    private Integer quantity;

    private Integer fkProduct;

    @NotNull(message = "paymentType must not be null")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public ExpenseRequestDTO() {
    }

    public ExpenseRequestDTO(LocalDate date, ExpenseCategory expenseCategory, String description, Double amountSpend, Integer quantity, Integer fkProduct, PaymentType paymentType) {
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.amountSpend = amountSpend;
        this.quantity = quantity;
        this.fkProduct = fkProduct;
        this.paymentType = paymentType;
    }

    public @NotNull(message = "date must not be null") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "date must not be null") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "expenseCategory must not be null") ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(@NotNull(message = "expenseCategory must not be null") ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public @NotBlank(message = "description is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "description is mandatory") String description) {
        this.description = description;
    }

    public @NotNull(message = "amountSpend is mandatory") Double getAmountSpend() {
        return amountSpend;
    }

    public void setAmountSpend(@NotNull(message = "amountSpend is mandatory") Double amountSpend) {
        this.amountSpend = amountSpend;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(Integer fkProduct) {
        this.fkProduct = fkProduct;
    }

    public @NotNull(message = "paymentType must not be null") PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NotNull(message = "paymentType must not be null") PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
