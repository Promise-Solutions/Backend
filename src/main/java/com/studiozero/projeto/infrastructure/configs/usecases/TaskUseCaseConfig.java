package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.TaskRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.TaskRepository;
import com.studiozero.projeto.application.usecases.task.CreateTaskUseCase;
import com.studiozero.projeto.application.usecases.task.DeleteTaskUseCase;
import com.studiozero.projeto.application.usecases.task.GetTaskUseCase;
import com.studiozero.projeto.application.usecases.task.ListTasksUseCase;
import com.studiozero.projeto.application.usecases.task.UpdateTaskUseCase;

@Configuration
public class TaskUseCaseConfig {
    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
        return new CreateTaskUseCase(taskRepository);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepository taskRepository) {
        return new DeleteTaskUseCase(taskRepository);
    }

    @Bean
    public GetTaskUseCase getTaskUseCase(TaskRepository taskRepository) {
        return new GetTaskUseCase(taskRepository);
    }

    @Bean
    public ListTasksUseCase listTasksUseCase(TaskRepository taskRepository) {
        return new ListTasksUseCase(taskRepository);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskRepository taskRepository) {
        return new UpdateTaskUseCase(taskRepository);
    }

    @Bean
    public TaskRepositoryImpl taskRepositoryImpl(JpaTaskRepository jpaTaskRepository) {
        return new TaskRepositoryImpl(jpaTaskRepository);
    }
}

