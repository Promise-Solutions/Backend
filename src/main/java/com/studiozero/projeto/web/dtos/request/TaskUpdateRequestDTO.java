package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TaskUpdateRequestDTO {
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate limitDate;

    private UUID fkEmployee;

    private UUID fkAssigned;

    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskUpdateRequestDTO() {
    }

    public TaskUpdateRequestDTO(String title, String description, LocalDate startDate, LocalDate limitDate, UUID fkEmployee, UUID fkAssigned, Status status) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fkEmployee = fkEmployee;
        this.fkAssigned = fkAssigned;
        this.status = status;
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
