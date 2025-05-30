package com.studiozero.projeto.dtos.request;

import com.studiozero.projeto.enums.ExpenseCategory;
import com.studiozero.projeto.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
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

}
