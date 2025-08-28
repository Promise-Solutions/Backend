package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.GoalRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.goal.CreateGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.DeleteGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetMostRecentGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.ListGoalsUseCase;
import com.studiozero.projeto.application.usecases.goal.UpdateGoalUseCase;

@Configuration
public class GoalUseCaseConfig {
    @Bean
    public CreateGoalUseCase createGoalUseCase(GoalRepositoryImpl goalRepository) {
        return new CreateGoalUseCase(goalRepository);
    }

    @Bean
    public DeleteGoalUseCase deleteGoalUseCase(GoalRepositoryImpl goalRepository) {
        return new DeleteGoalUseCase(goalRepository);
    }

    @Bean
    public GetGoalUseCase getGoalUseCase(GoalRepositoryImpl goalRepository) {
        return new GetGoalUseCase(goalRepository);
    }

    @Bean
    public GetMostRecentGoalUseCase getMostRecentGoalUseCase(GoalRepositoryImpl goalRepository) {
        return new GetMostRecentGoalUseCase(goalRepository);
    }

    @Bean
    public ListGoalsUseCase listGoalsUseCase(GoalRepositoryImpl goalRepository) {
        return new ListGoalsUseCase(goalRepository);
    }

    @Bean
    public UpdateGoalUseCase updateGoalUseCase(GoalRepositoryImpl goalRepository) {
        return new UpdateGoalUseCase(goalRepository);
    }
}

