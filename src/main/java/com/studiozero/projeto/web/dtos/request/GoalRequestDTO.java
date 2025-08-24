package com.studiozero.projeto.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GoalRequestDTO {
    @NotNull
    @Positive
    private Double goal;

    public GoalRequestDTO() {
    }

    public GoalRequestDTO(Double goal) {
        this.goal = goal;
    }

    public @NotNull @Positive Double getGoal() {
        return goal;
    }

    public void setGoal(@NotNull @Positive Double goal) {
        this.goal = goal;
    }
}
