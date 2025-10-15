package com.studiozero.projeto.application.usecases.task;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class ListTodayTasksUseCase {
    private final TaskRepository taskRepository;

    public ListTodayTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> execute() {
        LocalDate todayDate = LocalDate.now();
        return taskRepository.findByTodayDate(todayDate);
    }
}
