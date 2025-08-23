package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TaskResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate limitDate;
    private UUID fkEmployee;
    private UUID fkAssigned;
    private Status status;

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.startDate = task.getStartDate();
        this.limitDate = task.getLimitDate();
        this.fkEmployee = task.getEmployee() != null ? task.getEmployee().getId() : null;
        this.fkAssigned = task.getAssign() != null ? task.getAssign().getId() : null;
        this.status = task.getStatus();
    }
}
