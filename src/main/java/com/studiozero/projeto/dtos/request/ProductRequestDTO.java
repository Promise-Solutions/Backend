package com.studiozero.projeto.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductRequestDTO {

    @NotBlank(message = "name value is mandatory")
    private String name;

//    Esse atributo não vai ser requisitado no bar, apenas no lancamento da despesa
//    @NotNull(message = "qtdProduct value is mandatory")
//    @PositiveOrZero(message = "qtdProduct must be a positive number")
//    private Integer quantity;

    @NotNull(message = "clientValue  is mandatory")
    @Positive
    private Double clientValue;

    @NotNull(message = "internalValue  is mandatory")
    @Positive
    private Double internalValue;

//    essa regra vai lá para despesas
//    @NotNull(message = "totalBuyValue is mandatory")
//    @Positive
//    private Double totalBuyValue;
}
