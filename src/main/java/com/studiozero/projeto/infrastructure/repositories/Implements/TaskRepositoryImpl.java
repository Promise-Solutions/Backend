package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final JpaTaskRepository jpaTaskRepository;

    @Autowired
    public TaskRepositoryImpl(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }

    @Override
    public Task findById(UUID id) {
        return jpaTaskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        jpaTaskRepository.save(task);
    }

    @Override
    public void deleteById(UUID id) {
        jpaTaskRepository.deleteById(id);
    }
}
