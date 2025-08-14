package com.studiozero.projeto.application.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@AllArgsConstructor
public class SubJobDeleteResponseDTO {
    private UUID id;
    private Status jobStatus;
    private Double jobTotalValue;

    public SubJobDeleteResponseDTO(UUID id, Job job) {
        this.id = id;
        this.jobStatus = job.getStatus();
        this.jobTotalValue = job.getTotalValue();
    }
}
