package com.studiozero.projeto.operations.appllication.usecases.goal;

import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;

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
