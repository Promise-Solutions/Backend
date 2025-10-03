package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.task.*;
import com.studiozero.projeto.infrastructure.repositories.Implements.TaskRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseConfig {
    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepositoryImpl taskRepository) {
        return new CreateTaskUseCase(taskRepository);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepositoryImpl taskRepository) {
        return new DeleteTaskUseCase(taskRepository);
    }

    @Bean
    public GetTaskUseCase getTaskUseCase(TaskRepositoryImpl taskRepository) {
        return new GetTaskUseCase(taskRepository);
    }

    @Bean
    public ListTasksUseCase listTasksUseCase(TaskRepositoryImpl taskRepository) {
        return new ListTasksUseCase(taskRepository);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskRepositoryImpl taskRepository) {
        return new UpdateTaskUseCase(taskRepository);
    }

    @Bean
    public ListTodayTasksUseCase listTodayTasksUseCase (TaskRepositoryImpl taskRepository) {
        return new ListTodayTasksUseCase(taskRepository);
    }
}

