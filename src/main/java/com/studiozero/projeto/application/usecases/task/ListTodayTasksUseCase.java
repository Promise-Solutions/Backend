package com.studiozero.projeto.application.usecases.task;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class ListTodayTasksUseCase {
    private final TaskRepository taskRepository;

    public ListTodayTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> execute() {
        LocalDate todayDate = LocalDate.now();
        return taskRepository.findByTodayDate(todayDate);
    }
}
