package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobUpdateStatusRequestDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private UUID jobId;

    public SubJobUpdateStatusRequestDTO() {
    }

    public SubJobUpdateStatusRequestDTO(Status status, UUID jobId) {
        this.status = status;
        this.jobId = jobId;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public @NotNull UUID getJobId() {
        return jobId;
    }

    public void setJobId(@NotNull UUID jobId) {
        this.jobId = jobId;
    }
}
