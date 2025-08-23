package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.web.dtos.request.GoalRequestDTO;
import com.studiozero.projeto.web.dtos.response.GoalResponseDTO;
import java.util.List;
import java.util.UUID;

public class GoalMapper {
    public static Goal toDomain(GoalRequestDTO dto) {
        if (dto == null) return null;
        return new Goal(null, dto.getGoal());
    }

    public static Goal toDomain(GoalRequestDTO dto, Integer id) {
        if (dto == null || id == null) return null;
        return new Goal(id, dto.getGoal());
    }

    public static GoalResponseDTO toDTO(Goal goal) {
        if (goal == null) return null;
        return new GoalResponseDTO(goal);
    }

    public static List<GoalResponseDTO> toDTOList(List<Goal> entities) {
        if (entities == null) return null;
        return entities.stream().map(GoalMapper::toDTO).toList();
    }
}
