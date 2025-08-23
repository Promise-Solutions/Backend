package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.GoalRepository;
import com.studiozero.projeto.application.usecases.goal.CreateGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.DeleteGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetMostRecentGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.ListGoalsUseCase;
import com.studiozero.projeto.application.usecases.goal.UpdateGoalUseCase;

@Configuration
public class GoalUseCaseConfig {
    @Bean
    public CreateGoalUseCase createGoalUseCase(GoalRepository goalRepository) {
        return new CreateGoalUseCase(goalRepository);
    }

    @Bean
    public DeleteGoalUseCase deleteGoalUseCase(GoalRepository goalRepository) {
        return new DeleteGoalUseCase(goalRepository);
    }

    @Bean
    public GetGoalUseCase getGoalUseCase(GoalRepository goalRepository) {
        return new GetGoalUseCase(goalRepository);
    }

    @Bean
    public GetMostRecentGoalUseCase getMostRecentGoalUseCase(GoalRepository goalRepository) {
        return new GetMostRecentGoalUseCase(goalRepository);
    }

    @Bean
    public ListGoalsUseCase listGoalsUseCase(GoalRepository goalRepository) {
        return new ListGoalsUseCase(goalRepository);
    }

    @Bean
    public UpdateGoalUseCase updateGoalUseCase(GoalRepository goalRepository) {
        return new UpdateGoalUseCase(goalRepository);
    }
}

