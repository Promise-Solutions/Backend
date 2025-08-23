package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="despesa")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

