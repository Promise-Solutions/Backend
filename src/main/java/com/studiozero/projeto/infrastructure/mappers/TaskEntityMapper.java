package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.infrastructure.entities.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskEntityMapper {
    public static Task toDomain(TaskEntity entity) {
        if (entity == null) return null;
        return new Task(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getStartDate(),
            entity.getLimitDate(),
            EmployeeEntityMapper.toDomain(entity.getEmployee()),
            entity.getStatus(),
            EmployeeEntityMapper.toDomain(entity.getAssign())
        );
    }

    public static TaskEntity toEntity(Task task) {
        if (task == null) return null;
        return new TaskEntity(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStartDate(),
            task.getLimitDate(),
            EmployeeEntityMapper.toEntity(task.getEmployee()),
            task.getStatus(),
            EmployeeEntityMapper.toEntity(task.getAssign())
        );
    }

    public static List<Task> toDomainList(List<TaskEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(TaskEntityMapper::toDomain).toList();
    }

    public static List<TaskEntity> toEntityList(List<Task> tasks) {
        if (tasks == null) return null;
        return tasks.stream().map(TaskEntityMapper::toEntity).toList();
    }
}
