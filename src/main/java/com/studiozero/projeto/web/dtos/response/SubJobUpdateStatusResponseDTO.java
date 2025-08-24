package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobUpdateStatusResponseDTO {
    private UUID subJobId;
    private Status subJobStatus;
    private Status jobStatus;

    public SubJobUpdateStatusResponseDTO(SubJob subJob, Job job) {
        this.subJobId = subJob.getId();
        this.subJobStatus = subJob.getStatus();
        this.jobStatus = job.getStatus();
    }

    public SubJobUpdateStatusResponseDTO(SubJob subJob, Status jobStatus) {
        this.subJobId = subJob.getId();
        this.subJobStatus = subJob.getStatus();
        this.jobStatus = jobStatus;
    }

    public SubJobUpdateStatusResponseDTO() {
    }

    public SubJobUpdateStatusResponseDTO(UUID subJobId, Status subJobStatus, Status jobStatus) {
        this.subJobId = subJobId;
        this.subJobStatus = subJobStatus;
        this.jobStatus = jobStatus;
    }

    public UUID getSubJobId() {
        return subJobId;
    }

    public void setSubJobId(UUID subJobId) {
        this.subJobId = subJobId;
    }

    public Status getSubJobStatus() {
        return subJobStatus;
    }

    public void setSubJobStatus(Status subJobStatus) {
        this.subJobStatus = subJobStatus;
    }

    public Status getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Status jobStatus) {
        this.jobStatus = jobStatus;
    }
}
