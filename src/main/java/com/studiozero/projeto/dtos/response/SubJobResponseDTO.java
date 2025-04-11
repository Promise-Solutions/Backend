package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private Double value;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private UUID fkService;

    public SubJobResponseDTO(SubJob subJob) {
        this.id = subJob.getId();
        this.title = subJob.getTitle();
        this.description = subJob.getDescription();
        this.value = subJob.getValue();
        this.date = subJob.getDate();
        this.startTime = subJob.getStartTime();
        this.endTime = subJob.getEndTime();
        this.status = subJob.getStatus();
        this.fkService = subJob.getFkService();
    }
}
