package com.studiozero.projeto.operations.appllication.usecases.task;

import java.util.UUID;

import com.studiozero.projeto.operations.domain.entities.Task;
import com.studiozero.projeto.operations.domain.repositories.TaskRepository;

public class GetTaskUseCase {
    private final TaskRepository taskRepository;

    public GetTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(UUID id) {
        return taskRepository.findById(id);
    }
}
