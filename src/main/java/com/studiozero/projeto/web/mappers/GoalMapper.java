package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.web.dtos.request.GoalRequestDTO;
import com.studiozero.projeto.web.dtos.response.GoalResponseDTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoalMapper {
    public static Goal toEntity(GoalRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Goal(null, dto.getGoal());
    }

    public static GoalResponseDTO toDTO(Goal goal) {
        if (goal == null) {
            return null;
        }
        return new GoalResponseDTO(goal);
    }

    public static List<GoalResponseDTO> toListDtos(List<Goal> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(GoalMapper::toDTO)
                .toList();
    }

    public static Goal toEntity(GoalRequestDTO dto, java.util.UUID id) {
        if (dto == null || id == null) {
            return null;
        }
        return new Goal(null, dto.getGoal());
    }
}
