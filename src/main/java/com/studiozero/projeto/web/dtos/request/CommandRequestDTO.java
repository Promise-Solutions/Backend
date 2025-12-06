package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandRequestDTO {

    @NotNull(message = "Command Number is mandatory")
    private Integer commandNumber;

    @NotNull(message = "Opening date and time is mandatory")
    private LocalDateTime openingDateTime;

    private LocalDateTime closingDateTime;

    private Double discount;

    @NotNull(message = "Total value is mandatory")
    private Double totalValue;

    private UUID fkClient;

    @NotNull(message = "Employee ID is mandatory")
    private UUID fkEmployee;

    @NotNull(message = "Status is mandatory")
    private Status status;

    public CommandRequestDTO() {
    }

    public CommandRequestDTO(Integer commandNumber, LocalDateTime openingDateTime, LocalDateTime closingDateTime, Double discount, Double totalValue, @Nullable UUID fkClient, UUID fkEmployee, Status status) {
        this.commandNumber = commandNumber;
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
        this.discount = discount;
        this.totalValue = totalValue;
        this.fkClient = fkClient;
        this.fkEmployee = fkEmployee;
        this.status = status;
    }

    public @NotNull(message = "Command Number is mandatory") Integer getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(@NotNull(message = "Command Number is mandatory") Integer commandNumber) {
        this.commandNumber = commandNumber;
    }

    public @NotNull(message = "Opening date and time is mandatory") LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    public void setOpeningDateTime(@NotNull(message = "Opening date and time is mandatory") LocalDateTime openingDateTime) {
        this.openingDateTime = openingDateTime;
    }

    public LocalDateTime getClosingDateTime() {
        return closingDateTime;
    }

    public void setClosingDateTime(LocalDateTime closingDateTime) {
        this.closingDateTime = closingDateTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public @NotNull(message = "Total value is mandatory") Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(@NotNull(message = "Total value is mandatory") Double totalValue) {
        this.totalValue = totalValue;
    }

    @Nullable
    public UUID getFkClient() {
        return fkClient;
    }

    public void setFkClient(@Nullable UUID fkClient) {
        this.fkClient = fkClient;
    }

    public @NotNull(message = "Employee ID is mandatory") UUID getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(@NotNull(message = "Employee ID is mandatory") UUID fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public @NotNull(message = "Status is mandatory") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is mandatory") Status status) {
        this.status = status;
    }
}
