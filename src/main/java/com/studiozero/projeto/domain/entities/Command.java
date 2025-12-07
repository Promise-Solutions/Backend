package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDateTime;

public class Command {
    private Integer id;
    private Integer commandNumber;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Double discount;
    private Double totalValue;
    private Client client;
    private Employee employee;
    private Boolean isInternal;
    private Status status;

    public Command(Integer id, Integer commandNumber, LocalDateTime openingDateTime, LocalDateTime closingDateTime,
            Double discount, Double totalValue, Client client, Employee employee, Boolean isInternal, Status status) {
        validateCommandNumber(commandNumber);
        validateOpeningDateTime(openingDateTime);
        validateTotalValue(totalValue);
        defineIsInternal();
        validateStatus(status);
        this.id = id;
        this.commandNumber = commandNumber;
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
        this.discount = discount;
        this.totalValue = totalValue;
        this.client = client;
        this.employee = employee;
        this.isInternal = isInternal;
        this.status = status;
    }

    public Command() {

    }

    private void validateCommandNumber(Integer commandNumber) {
        if (commandNumber == null || commandNumber <= 0) {
            throw new IllegalArgumentException("Command number must be greater than zero");
        }
    }

    private void validateOpeningDateTime(LocalDateTime openingDateTime) {
        if (openingDateTime == null) {
            throw new IllegalArgumentException("Opening date/time cannot be null");
        }
    }

    private void validateTotalValue(Double totalValue) {
        if (totalValue == null || totalValue < 0) {
            throw new IllegalArgumentException("Total value cannot be null or negative");
        }
    }

    private void validateIsInternal(Boolean isInternal) {
        if (isInternal == null) {
            throw new IllegalArgumentException("isInternal cannot be null");
        }
    }

    private void validateStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }

    public void defineIsInternal() {
        if (this.client == null) {
            this.isInternal = true;
        } else {
            this.isInternal = false;
        };
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
