package com.studiozero.projeto.operations.domain.repositories;

import java.util.UUID;

import com.studiozero.projeto.operations.domain.entities.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(UUID id);

    List<Task> findAll();

    void save(Task task);

    void deleteById(UUID id);
}
