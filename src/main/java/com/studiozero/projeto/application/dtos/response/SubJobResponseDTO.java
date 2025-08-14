package com.studiozero.projeto.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
