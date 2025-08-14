package com.studiozero.projeto.application.dtos.response;

import com.studiozero.projeto.domain.entities.Goal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalResponseDTO {
    private Integer id;
    private Double goal;

    public GoalResponseDTO(Goal entity) {
        this.id = entity.getId();
        this.goal = entity.getGoal();
    }
}
