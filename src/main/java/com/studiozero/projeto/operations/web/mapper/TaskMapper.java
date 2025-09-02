package com.studiozero.projeto.operations.web.mapper;

import com.studiozero.projeto.operations.domain.entities.Task;
import com.studiozero.projeto.operations.web.dto.request.TaskRequestDTO;
import com.studiozero.projeto.operations.web.dto.request.TaskUpdateRequestDTO;
import com.studiozero.projeto.operations.web.dto.response.TaskResponseDTO;
import com.studiozero.projeto.users.domain.entities.Employee;
import java.util.List;
import java.util.UUID;

public class TaskMapper {
    public static Task toDomain(TaskRequestDTO dto, Employee employee, Employee assign) {
        if (dto == null) return null;
        return new Task(
            UUID.randomUUID(),
            dto.getTitle(),
            dto.getDescription(),
            dto.getStartDate(),
            dto.getLimitDate(),
            employee,
            dto.getStatus(),
            assign
        );
    }

    public static Task toDomain(TaskUpdateRequestDTO dto, UUID id, Employee employee, Employee assign) {
        if (dto == null || id == null) return null;
        return new Task(
            id,
            dto.getTitle(),
            dto.getDescription(),
            dto.getStartDate(),
            dto.getLimitDate(),
            employee,
            dto.getStatus(),
            assign
        );
    }

    public static TaskResponseDTO toDTO(Task task) {
        if (task == null) return null;
        return new TaskResponseDTO(task);
    }

    public static List<TaskResponseDTO> toDTOList(List<Task> entities) {
        if (entities == null) return null;
        return entities.stream().map(TaskMapper::toDTO).toList();
    }
}
