package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="despesa")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa", nullable = false)
    private Integer id;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "quantidade")
    private Integer quantity;

    @Positive
    @Column(name = "valor", nullable = false)
    private Double amountSpend;

    @ManyToOne
    @JoinColumn(name = "fk_produto", nullable = true)
    private ProductEntity product;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_pagamento", nullable = false)
    private PaymentType paymentType;

    public ExpenseEntity() {
    }

    public ExpenseEntity(Integer id, LocalDate date, ExpenseCategory expenseCategory, String description, Integer quantity, Double amountSpend, ProductEntity product, PaymentType paymentType) {
        this.id = id;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.description = description;
        this.quantity = quantity;
        this.amountSpend = amountSpend;
        this.product = product;
        this.paymentType = paymentType;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public @Positive Double getAmountSpend() {
        return amountSpend;
    }

    public void setAmountSpend(@Positive Double amountSpend) {
        this.amountSpend = amountSpend;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}

