package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStartDate(dto.getStartDate());
        task.setLimitDate(dto.getLimitDate());
        task.setStatus(dto.getStatus());
        task.setFkEmployee(dto.getFkEmployee());

        return task;
    }

    public TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(task);
    }


}
