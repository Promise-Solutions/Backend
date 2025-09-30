package com.studiozero.projeto.domain.repositories;

import java.time.LocalDate;
import java.util.UUID;

import com.studiozero.projeto.domain.entities.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(UUID id);

    List<Task> findAll();

    void save(Task task);

    void deleteById(UUID id);

    List<Task> findByTodayDate(LocalDate todayDate);
}
