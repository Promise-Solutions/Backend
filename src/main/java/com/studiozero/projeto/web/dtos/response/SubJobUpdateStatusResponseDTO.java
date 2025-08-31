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
    private String subJobStatus;
    private String jobStatus;

    public SubJobUpdateStatusResponseDTO(SubJob subJob, Job job) {
        this.subJobId = subJob.getId();
        this.subJobStatus = subJob.getStatus().toString();
        this.jobStatus = job.getStatus().toString();
    }

    public SubJobUpdateStatusResponseDTO(SubJob subJob, Status jobStatus) {
        this.subJobId = subJob.getId();
        this.subJobStatus = subJob.getStatus().toString();
        this.jobStatus = jobStatus.toString();
    }

    public SubJobUpdateStatusResponseDTO() {
    }

    public SubJobUpdateStatusResponseDTO(UUID subJobId, Status subJobStatus, Status jobStatus) {
        this.subJobId = subJobId;
        this.subJobStatus = subJobStatus.toString();
        this.jobStatus = jobStatus.toString();
    }

    public UUID getSubJobId() {
        return subJobId;
    }

    public void setSubJobId(UUID subJobId) {
        this.subJobId = subJobId;
    }

    public String getSubJobStatus() {
        return subJobStatus;
    }

    public void setSubJobStatus(String subJobStatus) {
        this.subJobStatus = subJobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
