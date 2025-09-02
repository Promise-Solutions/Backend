package com.studiozero.projeto.operations.appllication.usecases.task;

import java.util.UUID;

import com.studiozero.projeto.operations.domain.repositories.TaskRepository;

public class DeleteTaskUseCase {
    private final TaskRepository taskRepository;

    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(UUID id) {
        taskRepository.deleteById(id);
    }
}
