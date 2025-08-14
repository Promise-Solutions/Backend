package com.studiozero.projeto.application.services;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.application.enums.Context;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final TracingService tracingService;

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal findGoalById(Integer id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found"));
    }

    public List<Goal> listGoals() {
        return goalRepository.findAll();
    }

    public Goal findMostRecentGoal() {
        return goalRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new NotFoundException("No Goal found."));
    }

    public Goal updateGoal(Goal goal) {
        if(goal.getId() == null) {
            return createGoal(goal);
        }

        if (goalRepository.existsById(goal.getId())) {
            goal.setId(goal.getId());
            tracingService.setTracing(Context.EXPENSE);
            return goalRepository.save(goal);
        }
        throw new NotFoundException("Goal not found");
    }

    public void deleteGoal(Integer id) {
        if (goalRepository.existsById(id)) {
            tracingService.setTracing(Context.EXPENSE);
            goalRepository.deleteById(id);
        } else {
            throw new NotFoundException("Goal not found");
        }
    }
}
