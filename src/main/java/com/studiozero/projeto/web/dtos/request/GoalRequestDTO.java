package com.studiozero.projeto.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class GoalRequestDTO {
    @NotNull
    @PositiveOrZero
    private Double value;

    public GoalRequestDTO() {
    }

    public GoalRequestDTO(Double value) {
        this.value = value;
    }

    public @NotNull @PositiveOrZero Double getValue() {
        return value;
    }

    public void setValue(@NotNull @PositiveOrZero Double value) {
        this.value = value;
    }
}
