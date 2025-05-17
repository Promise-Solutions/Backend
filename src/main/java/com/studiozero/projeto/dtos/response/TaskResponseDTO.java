package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Task;
import com.studiozero.projeto.enums.Status;
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
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fkEmployee = fkEmployee;
        this.fkAssigned = fkAssigned;
        this.status = status;
    }
}
