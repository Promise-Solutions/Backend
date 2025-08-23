package com.studiozero.projeto.domain.entities;

public class Goal {
    private Integer id;
    private Double goal;

    public Goal(Integer id, Double goal) {
        validateGoal(goal);
        this.id = id;
        this.goal = goal;
    }

    private void validateGoal(Double goal) {
        if (goal == null || goal <= 0) {
            throw new IllegalArgumentException("Goal must be greater than zero");
        }
    }

    public Integer getId() {
        return id;
    }

    public Double getGoal() {
        return goal;
    }

    public void changeGoal(Double newGoal) {
        validateGoal(newGoal);
        this.goal = newGoal;
    }
}
