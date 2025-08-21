package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.web.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.web.dtos.request.TaskUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.TaskResponseDTO;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Task(
                java.util.UUID.randomUUID(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getLimitDate(),
                null, // employee deve ser resolvido pelo service/usecase
                dto.getStatus(),
                null // assign deve ser resolvido pelo service/usecase
        );
    }

    public static TaskResponseDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskResponseDTO(task);
    }

    public static List<TaskResponseDTO> toListDtos(List<Task> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public Task toEntity(TaskUpdateRequestDTO dto, UUID id) {
        if (dto == null || id == null)
            return null;
        return new Task(
                id,
                dto.getTitle(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getLimitDate(),
                null, // employee deve ser resolvido pelo service/usecase
                dto.getStatus(),
                null // assign deve ser resolvido pelo service/usecase
        );
    }
}
