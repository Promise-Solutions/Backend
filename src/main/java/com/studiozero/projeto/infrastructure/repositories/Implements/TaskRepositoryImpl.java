package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.domain.repositories.TaskRepository;
import com.studiozero.projeto.infrastructure.entities.TaskEntity;
import com.studiozero.projeto.infrastructure.mappers.TaskEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.JpaTaskRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final JpaTaskRepository jpaTaskRepository;

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
