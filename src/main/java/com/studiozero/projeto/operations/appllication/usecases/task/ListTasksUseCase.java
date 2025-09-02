package com.studiozero.projeto.operations.appllication.usecases.task;

import com.studiozero.projeto.operations.domain.entities.Task;
import com.studiozero.projeto.operations.domain.repositories.TaskRepository;
import java.util.List;

public class ListTasksUseCase {
    private final TaskRepository taskRepository;

    public ListTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> execute() {
        return taskRepository.findAll();
    }
}
