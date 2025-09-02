package com.studiozero.projeto.operations.web.dto.response;


import com.studiozero.projeto.operations.domain.entities.Goal;

public class GoalResponseDTO {
    private Integer id;
    private Double goal;

    public GoalResponseDTO(Goal entity) {
        this.id = entity.getId();
        this.goal = entity.getGoal();
    }

    public GoalResponseDTO() {
    }

    public GoalResponseDTO(Integer id, Double goal) {
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
