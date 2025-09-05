package com.studiozero.projeto.web.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandResponseDTO {
    private Integer id;
    private Integer commandNumber;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Double discount;
    private Double totalValue;
    private UUID fkClient;
    private UUID fkEmployee;
    private Status status;
    private Boolean isInternal;

    public CommandResponseDTO(Command command) {
        this.id = command.getId();
        this.commandNumber = command.getCommandNumber();
        this.openingDateTime = command.getOpeningDateTime();
        this.closingDateTime = command.getClosingDateTime();
        this.discount = command.getDiscount();
        this.totalValue = command.getTotalValue();
        this.fkClient = command.getClient() != null ? command.getClient().getId() : null;
        this.fkEmployee = command.getEmployee() != null ? command.getEmployee().getId() : null;
        this.status = command.getStatus();
        this.isInternal = command.getInternal();
    }

    public CommandResponseDTO() {
    }

    public CommandResponseDTO(Integer id, Integer commandNumber, LocalDateTime openingDateTime, LocalDateTime closingDateTime, Double discount, Double totalValue, UUID fkClient, UUID fkEmployee, Status status, Boolean isInternal) {
        this.id = id;
        this.commandNumber = commandNumber;
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
        this.discount = discount;
        this.totalValue = totalValue;
        this.fkClient = fkClient;
        this.fkEmployee = fkEmployee;
        this.status = status;
        this.isInternal = isInternal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(Integer commandNumber) {
        this.commandNumber = commandNumber;
    }

    public LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    public void setOpeningDateTime(LocalDateTime openingDateTime) {
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

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public UUID getFkClient() {
        return fkClient;
    }

    public void setFkClient(UUID fkClient) {
        this.fkClient = fkClient;
    }

    public UUID getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(UUID fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }
}
