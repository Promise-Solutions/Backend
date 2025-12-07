package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;
import java.time.LocalDate;

public class Expense {
    private Integer id;
    private LocalDate date;
    private ExpenseCategory expenseCategory;
    private String description;
    private Integer quantity;
    private Double amountSpend;
    private Product product;
    private PaymentType paymentType;

    public Expense(Integer id, LocalDate date, ExpenseCategory expenseCategory, String description, Integer quantity,
            Double amountSpend, Product product, PaymentType paymentType) {
        validateDescription(description);
        validateAmountSpend(amountSpend);
        validateExpenseCategory(product, expenseCategory);
        this.id = id;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.quantity = quantity;
        this.amountSpend = amountSpend;
        this.product = product;
        this.paymentType = paymentType;
    }

    public Expense(Integer id, LocalDate date, ExpenseCategory expenseCategory, String description, Integer quantity,
            Double amountSpend, PaymentType paymentType) {
        validateDescription(description);
        validateAmountSpend(amountSpend);
        this.id = id;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.quantity = quantity;
        this.amountSpend = amountSpend;
        this.paymentType = paymentType;
    }

    public Expense() {

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

    private void validateExpenseCategory(Product product, ExpenseCategory expenseCategory) {
        if (expenseCategory != ExpenseCategory.STOCK && product != null) {
            throw new IllegalArgumentException("Only expenses with stock category can have a associated product");
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

    public Product getProduct() {
        return product;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAmountSpend(Double amountSpend) {
        this.amountSpend = amountSpend;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
