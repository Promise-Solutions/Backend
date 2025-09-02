package com.studiozero.projeto.operations.appllication.usecases.goal;


import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;

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
