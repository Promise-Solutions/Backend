package com.studiozero.projeto.application.usecases.goal;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;
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
