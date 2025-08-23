package com.studiozero.projeto.application.usecases.goal;


import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;

public class GetGoalUseCase {
    private final GoalRepository goalRepository;

    public GetGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal execute(Integer id) {
        Goal goal = goalRepository.findById(id);
        if (goal == null) {
            throw new IllegalArgumentException("Meta não encontrada");
        }
        return goal;
    }
}
