package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.dtos.request.GoalRequestDTO;
import com.studiozero.projeto.application.dtos.response.GoalResponseDTO;
import com.studiozero.projeto.domain.entities.Goal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoalMapper {
    public static Goal toEntity(GoalRequestDTO dto) {
        Goal goal = new Goal();

        goal.setGoal(dto.getGoal());

        return goal;
    }

    public static GoalResponseDTO toDTO(Goal goal) {
        if (goal == null) {
            return null;
        }
        GoalResponseDTO dto = new GoalResponseDTO();

        dto.setId(goal.getId());
        dto.setGoal(goal.getGoal());

        return dto;
    }

    public static List<GoalResponseDTO> toListDtos(List<Goal> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(GoalMapper::toDTO)
                .toList();
    }

    public static Goal toEntity(GoalRequestDTO dto, Integer id) {
        if (dto == null) {
            return null;
        }
       Goal goal = new Goal();

        goal.setId(id);
        if (dto.getGoal() != null) goal.setGoal(dto.getGoal());


        return goal;
    }
}
