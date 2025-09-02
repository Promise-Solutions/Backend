package com.studiozero.projeto.operations.appllication.usecases.goal;


import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;

public class DeleteGoalUseCase {
    private final GoalRepository goalRepository;

    public DeleteGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void execute(Integer id) {
        Goal goal = goalRepository.findById(id);
        if (goal == null) {
            throw new IllegalArgumentException("Meta não encontrada");
        }
        goalRepository.deleteById(id);
    }
}
