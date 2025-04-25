package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.enums.JobType;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.mappers.SubJobMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private List<SubJobResponseDTO> subJobs;

    public JobResponseDTO(Job job) {
        this.id = id;
        this.fkClient = fkClient;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.serviceType = serviceType;
        this.subJobs = job.getSubJobs() != null ? SubJobMapper.toListDtos(job.getSubJobs()) : null;
    }
}

