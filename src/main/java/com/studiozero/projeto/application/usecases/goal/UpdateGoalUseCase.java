package com.studiozero.projeto.application.usecases.goal;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;

public class UpdateGoalUseCase {
    private final GoalRepository goalRepository;

    public UpdateGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute(Integer id, Goal goal) {
        if (goal == null || id == null) {
            throw new IllegalArgumentException("Meta inválida");
        }

        Goal existing = goalRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Meta não encontrada");
        }
        
        existing.changeGoal(goal.getValue());

        goalRepository.save(existing);
        return goal;
    }
}
