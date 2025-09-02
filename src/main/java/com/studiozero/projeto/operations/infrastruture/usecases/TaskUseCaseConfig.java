package com.studiozero.projeto.operations.infrastruture.usecases;

import com.studiozero.projeto.operations.infrastruture.repository.impl.TaskRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.operations.appllication.usecases.task.CreateTaskUseCase;
import com.studiozero.projeto.operations.appllication.usecases.task.DeleteTaskUseCase;
import com.studiozero.projeto.operations.appllication.usecases.task.GetTaskUseCase;
import com.studiozero.projeto.operations.appllication.usecases.task.ListTasksUseCase;
import com.studiozero.projeto.operations.appllication.usecases.task.UpdateTaskUseCase;

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
}

