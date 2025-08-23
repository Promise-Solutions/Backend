package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate limitDate;
    private Employee employee;
    private Status status;
    private Employee assign;

    public Task(UUID id, String title, String description, LocalDate startDate, LocalDate limitDate, Employee employee,
            Status status, Employee assign) {
        validateTitle(title);
        validateDescription(description);
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public Status getStatus() {
        return status;
    }

    public Employee getAssign() {
        return assign;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssign(Employee assign) {
        this.assign = assign;
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
