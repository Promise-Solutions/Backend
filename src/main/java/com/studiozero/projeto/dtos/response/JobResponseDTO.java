package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.enums.JobType;
import com.studiozero.projeto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class JobResponseDTO {
    private UUID id;
    private UUID fkClient;
    private String title;
    private Double totalValue;
    private JobCategory category;
    private Status status;
    private JobType serviceType;

    public JobResponseDTO(Job job) {
        this.id = job.getId();
        this.fkClient = job.getClient().getId();
        this.title = job.getTitle();
        this.totalValue = job.getTotalValue();
        this.category = job.getCategory();
        this.status = job.getStatus();
        this.serviceType = job.getServiceType();
    }
}

