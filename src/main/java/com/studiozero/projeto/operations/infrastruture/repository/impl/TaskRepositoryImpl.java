package com.studiozero.projeto.operations.infrastruture.repository.impl;

import com.studiozero.projeto.operations.domain.entities.Task;
import com.studiozero.projeto.operations.domain.repositories.TaskRepository;
import com.studiozero.projeto.operations.infrastruture.entity.TaskEntity;
import com.studiozero.projeto.operations.infrastruture.mapper.TaskEntityMapper;
import com.studiozero.projeto.operations.infrastruture.repository.jpa.JpaTaskRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final JpaTaskRepository jpaTaskRepository;

    public TaskRepositoryImpl(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }

    @Override
    public Task findById(UUID id) {
        return jpaTaskRepository.findById(id)
            .map(TaskEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream()
            .map(TaskEntityMapper::toDomain)
            .toList();
    }

    @Override
    public void save(Task task) {
        TaskEntity entity = TaskEntityMapper.toEntity(task);
        jpaTaskRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaTaskRepository.deleteById(id);
    }
}
