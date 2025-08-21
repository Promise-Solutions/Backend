package com.studiozero.projeto.application.usecases.task;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;

public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(Task task) {
        if (task == null || task.getId() == null || task.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Tarefa inválida");
        }
        taskRepository.save(task);
        return task;
    }
}
