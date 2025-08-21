package com.studiozero.projeto.application.usecases.task;

import java.util.UUID;

import com.studiozero.projeto.domain.repositories.TaskRepository;

public class DeleteTaskUseCase {
    private final TaskRepository taskRepository;

    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(UUID id) {
        taskRepository.deleteById(id);
    }
}
