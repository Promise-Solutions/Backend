package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.application.enums.Status;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobDeleteResponseDTO {
    private UUID id;
    private Status jobStatus;
    private Double jobTotalValue;

    public SubJobDeleteResponseDTO(UUID id, Job job) {
        this.id = id;
        this.jobStatus = job.getStatus();
        this.jobTotalValue = job.getTotalValue();
    }

    public SubJobDeleteResponseDTO() {
    }

    public SubJobDeleteResponseDTO(UUID id, Status jobStatus, Double jobTotalValue) {
        this.id = id;
        this.jobStatus = jobStatus;
        this.jobTotalValue = jobTotalValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
