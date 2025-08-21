package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class SubJob {
    private final UUID id;
    private String title;
    private String description;
    private Double value;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime expectedEndTime;
    private Boolean needsRoom;
    private Status status;
    private Job job;

    public SubJob(UUID id, String title, String description, Double value, LocalDate date, LocalTime startTime,
            LocalTime expectedEndTime, Boolean needsRoom, Status status, Job job) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        validateTitle(title);
        validateDescription(description);
        validateValue(value);
        validateNeedsRoom(needsRoom);
        validateStatus(status);
        validateJob(job);
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.value = value;
        this.date = date;
        this.startTime = startTime;
        this.expectedEndTime = expectedEndTime;
        this.needsRoom = needsRoom;
        this.status = status;
        this.job = job;
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

    private void validateValue(Double value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Value cannot be null or negative");
        }
    }

    private void validateNeedsRoom(Boolean needsRoom) {
        if (needsRoom == null) {
            throw new IllegalArgumentException("NeedsRoom cannot be null");
        }
    }

    private void validateStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }

    private void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }

    public Double getValue() {
        return value;
    }

    public void changeValue(Double newValue) {
        validateValue(newValue);
        this.value = newValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void changeDate(LocalDate newDate) {
        this.date = newDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void changeStartTime(LocalTime newStartTime) {
        this.startTime = newStartTime;
    }

    public LocalTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void changeExpectedEndTime(LocalTime newExpectedEndTime) {
        this.expectedEndTime = newExpectedEndTime;
    }

    public Boolean getNeedsRoom() {
        return needsRoom;
    }

    public void changeNeedsRoom(Boolean newNeedsRoom) {
        validateNeedsRoom(newNeedsRoom);
        this.needsRoom = newNeedsRoom;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        validateStatus(status);
        this.status = status;
    }

    public void changeStatus(Status newStatus) {
        validateStatus(newStatus);
        this.status = newStatus;
    }

    public Job getJob() {
        return job;
    }

    public void changeJob(Job newJob) {
        validateJob(newJob);
        this.job = newJob;
    }
}
