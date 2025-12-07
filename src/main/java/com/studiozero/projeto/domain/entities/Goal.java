package com.studiozero.projeto.domain.entities;

public class Goal {
    private Integer id;
    private Double value;

    public Goal(Integer id, Double value) {
        validateGoal(value);
        this.id = id;
        this.value = value;
    }

    public Goal() {
    }

    private void validateGoal(Double goal) {
        if (goal == null || goal <= 0) {
            throw new IllegalArgumentException("Goal must be greater than zero");
        }
    }

    public Integer getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void changeGoal(Double newGoal) {
        validateGoal(newGoal);
        this.value = newGoal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
