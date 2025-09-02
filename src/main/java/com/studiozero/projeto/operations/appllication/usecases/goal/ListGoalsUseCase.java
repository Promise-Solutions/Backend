package com.studiozero.projeto.operations.appllication.usecases.goal;

import com.studiozero.projeto.operations.domain.entities.Goal;
import com.studiozero.projeto.operations.domain.repositories.GoalRepository;
import java.util.List;

public class ListGoalsUseCase {
    private final GoalRepository goalRepository;

    public ListGoalsUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<Goal> execute() {
        return goalRepository.findAll();
    }
}
