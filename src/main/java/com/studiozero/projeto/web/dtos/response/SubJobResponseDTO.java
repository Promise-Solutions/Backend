package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private Double value;
    private LocalDate date;
    private Boolean needsRoom;
    private LocalTime startTime;
    private LocalTime expectedEndTime;
    private Status status;
    private UUID fkService;
    private Status jobStatus;
    private Double jobTotalValue;

    public SubJobResponseDTO(UUID id, String title, String description, Double value, LocalDate date, Boolean needsRoom, LocalTime startTime, LocalTime expectedEndTime, Status status, UUID fkService) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.value = value;
        this.date = date;
        this.needsRoom = needsRoom;
        this.startTime = startTime;
        this.expectedEndTime = expectedEndTime;
        this.status = status;
        this.fkService = fkService;
    }

    public SubJobResponseDTO(SubJob subJob) {
        this.id = subJob.getId();
        this.title = subJob.getTitle();
        this.description = subJob.getDescription();
        this.value = subJob.getValue();
        this.date = subJob.getDate();
        this.needsRoom = subJob.getNeedsRoom();
        this.startTime = subJob.getStartTime();
        this.expectedEndTime = subJob.getExpectedEndTime();
        this.status = subJob.getStatus();
        this.fkService = subJob.getJob().getId();
    }

    public SubJobResponseDTO(SubJob subJob, Double jobTotalValue) {
        this.id = subJob.getId();
        this.title = subJob.getTitle();
        this.description = subJob.getDescription();
        this.value = subJob.getValue();
        this.date = subJob.getDate();
        this.needsRoom = subJob.getNeedsRoom();
        this.startTime = subJob.getStartTime();
        this.expectedEndTime = subJob.getExpectedEndTime();
        this.status = subJob.getStatus();
        this.fkService = subJob.getJob().getId();
        this.jobTotalValue = jobTotalValue;
    }

    public SubJobResponseDTO() {
    }

    public SubJobResponseDTO(UUID id, String title, String description, Double value, LocalDate date, Boolean needsRoom, LocalTime startTime, LocalTime expectedEndTime, Status status, UUID fkService, Status jobStatus, Double jobTotalValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.value = value;
        this.date = date;
        this.needsRoom = needsRoom;
        this.startTime = startTime;
        this.expectedEndTime = expectedEndTime;
        this.status = status;
        this.fkService = fkService;
        this.jobStatus = jobStatus;
        this.jobTotalValue = jobTotalValue;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getNeedsRoom() {
        return needsRoom;
    }

    public void setNeedsRoom(Boolean needsRoom) {
        this.needsRoom = needsRoom;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(LocalTime expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getFkService() {
        return fkService;
    }

    public void setFkService(UUID fkService) {
        this.fkService = fkService;
    }

    public Status getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Status jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Double getJobTotalValue() {
        return jobTotalValue;
    }

    public void setJobTotalValue(Double jobTotalValue) {
        this.jobTotalValue = jobTotalValue;
    }
}
