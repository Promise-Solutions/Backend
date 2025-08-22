package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

public class TaskEntity {
    private final UUID id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate limitDate;
    private EmployeeEntity employeeEntity;
    private Status status;
    private EmployeeEntity assign;

    public TaskEntity(UUID id, String title, String description, LocalDate startDate, LocalDate limitDate, EmployeeEntity employeeEntity,
                      Status status, EmployeeEntity assign) {
        if (id == null || id.toString().isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        validateTitle(title);
        validateDescription(description);
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.employeeEntity = employeeEntity;
        this.status = status;
        this.assign = assign;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (title.length() < 2 || title.length() > 100) {
            throw new IllegalArgumentException("Title must be between 2 and 100 characters");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (description.length() < 2 || description.length() > 500) {
            throw new IllegalArgumentException("Description must be between 2 and 500 characters");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public EmployeeEntity getEmployee() {
        return employeeEntity;
    }

    public Status getStatus() {
        return status;
    }

    public EmployeeEntity getAssign() {
        return assign;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    public void changeDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }
}
