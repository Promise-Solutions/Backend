package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TaskRequestDTO {
    @NotBlank(message = "Title value is mandatory")
    private String title;

    @NotBlank(message = "Description value is mandatory")
    private String description;

    @NotNull(message = "Start date value is mandatory")
    private LocalDate startDate;

    private LocalDate limitDate;

    @Nullable
    private UUID fkEmployee;

    private UUID fkAssigned;

    @NotNull(message = "Status value is mandatory")
    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskRequestDTO() {
    }

    public TaskRequestDTO(String title, String description, LocalDate startDate, LocalDate limitDate, @Nullable UUID fkEmployee, UUID fkAssigned, Status status) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fkEmployee = fkEmployee;
        this.fkAssigned = fkAssigned;
        this.status = status;
    }

    public @NotBlank(message = "Title value is mandatory") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title value is mandatory") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Description value is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description value is mandatory") String description) {
        this.description = description;
    }

    public @NotNull(message = "Start date value is mandatory") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Start date value is mandatory") LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    @Nullable
    public UUID getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(@Nullable UUID fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public UUID getFkAssigned() {
        return fkAssigned;
    }

    public void setFkAssigned(UUID fkAssigned) {
        this.fkAssigned = fkAssigned;
    }

    public @NotNull(message = "Status value is mandatory") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status value is mandatory") Status status) {
        this.status = status;
    }
}
