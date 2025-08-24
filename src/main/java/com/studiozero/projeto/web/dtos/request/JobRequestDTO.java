package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class JobRequestDTO {

    @NotNull(message = "fkClient value is mandatory")
    private UUID fkClient;

    @NotBlank
    private String title;

    @NotNull(message = "Total value value is mandatory")
    @PositiveOrZero
    private Double totalValue;

    @NotNull(message = "Job Category value is mandatory")
    private JobCategory category;

    @NotNull(message = "Status value is mandatory")
    private Status status;

    @NotNull(message = "Job Type value is mandatory")
    private JobType serviceType;

    public JobRequestDTO() {
    }

    public JobRequestDTO(UUID fkClient, String title, Double totalValue, JobCategory category, Status status, JobType serviceType) {
        this.fkClient = fkClient;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.serviceType = serviceType;
    }

    public @NotNull(message = "fkClient value is mandatory") UUID getFkClient() {
        return fkClient;
    }

    public void setFkClient(@NotNull(message = "fkClient value is mandatory") UUID fkClient) {
        this.fkClient = fkClient;
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @NotNull(message = "Total value value is mandatory") @PositiveOrZero Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(@NotNull(message = "Total value value is mandatory") @PositiveOrZero Double totalValue) {
        this.totalValue = totalValue;
    }

    public @NotNull(message = "Job Category value is mandatory") JobCategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Job Category value is mandatory") JobCategory category) {
        this.category = category;
    }

    public @NotNull(message = "Status value is mandatory") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status value is mandatory") Status status) {
        this.status = status;
    }

    public @NotNull(message = "Job Type value is mandatory") JobType getServiceType() {
        return serviceType;
    }

    public void setServiceType(@NotNull(message = "Job Type value is mandatory") JobType serviceType) {
        this.serviceType = serviceType;
    }
}