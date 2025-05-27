package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.ExpenseCategory;
import com.studiozero.projeto.enums.PaymentType;
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

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Positive
    @Column(name = "valor", nullable = false)
    private Double amountSpend;

    @ManyToOne
    @JoinColumn(name = "fk_produto", nullable = true)
    private Product product;

    //como fazer a quantidade daqui ser somada lá em produto
    // se expensiveCategory != STOCK ou OTHERS, devemos travar o preenchimento do campo
//    @Column(name = "quantidade", nullable = true)
//    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_pagamento", nullable = false)
    private PaymentType paymentType;


}
