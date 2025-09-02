package com.studiozero.projeto.operations.appllication.usecases.goal;

import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;

public class GetMostRecentGoalUseCase {
    private final GoalRepository goalRepository;

    public GetMostRecentGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute() {
        return goalRepository.findTopByOrderByIdDesc().orElse(null);
    }
}
