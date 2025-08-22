package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDateTime;

public class CommandEntity {
    private final Integer id;
    private Integer commandNumber;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Double discount;
    private Double totalValue;
    private ClientEntity clientEntity;
    private EmployeeEntity employeeEntity;
    private Boolean isInternal;
    private Status status;

    public CommandEntity(Integer id, Integer commandNumber, LocalDateTime openingDateTime, LocalDateTime closingDateTime,
                         Double discount, Double totalValue, ClientEntity clientEntity, EmployeeEntity employeeEntity, Boolean isInternal, Status status) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        validateCommandNumber(commandNumber);
        validateOpeningDateTime(openingDateTime);
        validateTotalValue(totalValue);
        validateEmployee(employeeEntity);
        validateIsInternal(isInternal);
        validateStatus(status);
        this.id = id;
        this.commandNumber = commandNumber;
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
        this.discount = discount;
        this.totalValue = totalValue;
        this.clientEntity = clientEntity;
        this.employeeEntity = employeeEntity;
        this.isInternal = isInternal;
        this.status = status;
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

    private void validateEmployee(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            throw new IllegalArgumentException("Employee cannot be null");
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

    public Integer getId() {
        return id;
    }

    public Integer getCommandNumber() {
        return commandNumber;
    }

    public void changeCommandNumber(Integer newCommandNumber) {
        validateCommandNumber(newCommandNumber);
        this.commandNumber = newCommandNumber;
    }

    public LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    public void changeOpeningDateTime(LocalDateTime newOpeningDateTime) {
        validateOpeningDateTime(newOpeningDateTime);
        this.openingDateTime = newOpeningDateTime;
    }

    public LocalDateTime getClosingDateTime() {
        return closingDateTime;
    }

    public void changeClosingDateTime(LocalDateTime newClosingDateTime) {
        this.closingDateTime = newClosingDateTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void changeDiscount(Double newDiscount) {
        this.discount = newDiscount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void changeTotalValue(Double newTotalValue) {
        validateTotalValue(newTotalValue);
        this.totalValue = newTotalValue;
    }

    public ClientEntity getClient() {
        return clientEntity;
    }

    public void changeClient(ClientEntity newClientEntity) {
        this.clientEntity = newClientEntity;
    }

    public EmployeeEntity getEmployee() {
        return employeeEntity;
    }

    public void changeEmployee(EmployeeEntity newEmployeeEntity) {
        validateEmployee(newEmployeeEntity);
        this.employeeEntity = newEmployeeEntity;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void changeIsInternal(Boolean newIsInternal) {
        validateIsInternal(newIsInternal);
        this.isInternal = newIsInternal;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status newStatus) {
        validateStatus(newStatus);
        this.status = newStatus;
    }

    public void defineIsInternal() {
        this.isInternal = (this.clientEntity == null && this.employeeEntity != null);
    }
}
