package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.enums.ExpenseCategory;
import com.studiozero.projeto.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class ExpenseRequestDTO {

    //mostramos a data do dia na tela, mas possiblitamos que seja alterada
    @NotBlank
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "amountSpend is mandatory")
    private Double amountSpend;

    private Integer quantity;

    private Integer fkProduct;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
