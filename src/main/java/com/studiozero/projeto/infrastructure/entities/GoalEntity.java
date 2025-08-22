package com.studiozero.projeto.infrastructure.entities;

public class GoalEntity {
    private Integer id;
    private Double goal;

    public GoalEntity(Integer id, Double goal) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
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
