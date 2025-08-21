package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@AllArgsConstructor
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
}
