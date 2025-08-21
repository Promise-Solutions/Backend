package com.studiozero.projeto.application.usecases.goal;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;

public class GetMostRecentGoalUseCase {
    private final GoalRepository goalRepository;

    public GetMostRecentGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute() {
        return goalRepository.findTopByOrderByIdDesc().orElse(null);
    }
}
