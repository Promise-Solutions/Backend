package com.studiozero.projeto.application.usecases.goal;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;

public class CreateGoalUseCase {
    private final GoalRepository goalRepository;

    public CreateGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Meta inválida");
        }
        goalRepository.save(goal);
        return goal;
    }
}
