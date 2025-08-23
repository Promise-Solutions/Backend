package com.studiozero.projeto.application.usecases.goal;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;

public class UpdateGoalUseCase {
    private final GoalRepository goalRepository;

    public UpdateGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute(Goal goal) {
        if (goal == null || goal.getId() == null || goal.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Meta inválida");
        }
        Goal existing = goalRepository.findById(goal.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Meta não encontrada");
        }
        goalRepository.save(goal);
        return goal;
    }
}
