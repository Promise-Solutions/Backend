package com.studiozero.projeto.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "meta")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta", nullable = false)
    private Integer id;

    @Column(name = "meta", nullable = false)
    private Double goal;

    public void setName(String increaseRevenue) {
    }

    public void setTargetValue(double v) {
    }

    public GoalEntity() {
    }

    public GoalEntity(Integer id, Double goal) {
        this.id = id;
        this.goal = goal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }
}
