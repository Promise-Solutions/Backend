package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Task;
import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

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

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(UUID id, String title, String description, LocalDate startDate, LocalDate limitDate, UUID fkEmployee, UUID fkAssigned, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fkEmployee = fkEmployee;
        this.fkAssigned = fkAssigned;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public UUID getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(UUID fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public UUID getFkAssigned() {
        return fkAssigned;
    }

    public void setFkAssigned(UUID fkAssigned) {
        this.fkAssigned = fkAssigned;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
