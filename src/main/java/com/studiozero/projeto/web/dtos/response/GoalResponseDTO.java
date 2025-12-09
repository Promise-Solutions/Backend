package com.studiozero.projeto.web.dtos.response;


import com.studiozero.projeto.domain.entities.Goal;

public class GoalResponseDTO {
    private Integer id;
    private Double value;

    public GoalResponseDTO(Goal entity) {
        this.id = entity.getId();
        this.value = entity.getValue();
    }

    public GoalResponseDTO() {
    }

    public GoalResponseDTO(Integer id, Double value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
