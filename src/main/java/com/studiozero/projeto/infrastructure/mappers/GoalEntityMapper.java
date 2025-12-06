package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.infrastructure.entities.GoalEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GoalEntityMapper {
    public static Goal toDomain(GoalEntity entity) {
        if (entity == null) return null;
        return new Goal(entity.getId(), entity.getGoal());
    }

    public static GoalEntity toEntity(Goal goal) {
        if (goal == null) return null;
        return new GoalEntity(goal.getId(), goal.getValue());
    }

    public static List<Goal> toDomainList(List<GoalEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(GoalEntityMapper::toDomain).toList();
    }

    public static List<GoalEntity> toEntityList(List<Goal> goals) {
        if (goals == null) return null;
        return goals.stream().map(GoalEntityMapper::toEntity).toList();
    }
}
