package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.TaskRequestDTO;
import com.studiozero.projeto.dtos.response.TaskResponseDTO;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.entities.Task;
import com.studiozero.projeto.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskMapper {

    private final EmployeeRepository employeeRepository;

    public TaskMapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Task toEntity(TaskRequestDTO dto) {
        Employee employee = employeeRepository.findById(dto.getFkEmployee())
                .orElse(null);

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStartDate(dto.getStartDate());
        task.setLimitDate(dto.getLimitDate());
        task.setStatus(dto.getStatus());
        task.setEmployee(employee);

        return task;
    }

    public static TaskResponseDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStartDate(task.getStartDate());
        dto.setLimitDate(task.getLimitDate());
        dto.setStatus(task.getStatus());
        dto.setFkEmployee(task.getEmployee() != null ? task.getEmployee().getId() : null);
        return dto;
    }

    public static List<TaskResponseDTO> toListDtos(List<Task> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public Task toEntity(TaskRequestDTO dto, UUID id) {
        if (dto == null) return null;

        Employee employee = employeeRepository.findById(dto.getFkEmployee())
                .orElse(null);

        Task task = new Task();

        task.setId(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStartDate(dto.getStartDate());
        task.setLimitDate(dto.getLimitDate());
        task.setStatus(dto.getStatus());

        return task;
    }
}
