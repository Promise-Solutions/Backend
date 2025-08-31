package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;

import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class JobResponseDTO {
    private UUID id;
    private UUID fkClient;
    private String title;
    private Double totalValue;
    private JobCategory category;
    private Status status;
    private JobType serviceType;
    private List<SubJobResponseDTO> subJobs;

    public JobResponseDTO(Job job, List<SubJobResponseDTO> subJobResponseDTO) {
        this.id = job.getId();
        this.fkClient = job.getClient().getId();
        this.title = job.getTitle();
        this.totalValue = job.getTotalValue();
        this.category = job.getCategory();
        this.status = job.getStatus();
        this.serviceType = job.getServiceType();
        this.subJobs = subJobResponseDTO;
    }

    public JobResponseDTO() {
    }

    public JobResponseDTO(UUID id, UUID fkClient, String title, Double totalValue, JobCategory category, Status status, JobType serviceType, List<SubJobResponseDTO> subJobs) {
        this.id = id;
        this.fkClient = fkClient;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.serviceType = serviceType;
        this.subJobs = subJobs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFkClient() {
        return fkClient;
    }

    public void setFkClient(UUID fkClient) {
        this.fkClient = fkClient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public JobCategory getCategory() {
        return category;
    }

    public void setCategory(JobCategory category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobType getServiceType() {
        return serviceType;
    }

    public void setServiceType(JobType serviceType) {
        this.serviceType = serviceType;
    }

    public List<SubJobResponseDTO> getSubJobs() {
        return subJobs;
    }

    public void setSubJobs(List<SubJobResponseDTO> subJobs) {
        this.subJobs = subJobs;
    }
}

