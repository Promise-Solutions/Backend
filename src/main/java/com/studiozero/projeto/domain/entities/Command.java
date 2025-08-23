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
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        validateCommandNumber(commandNumber);
        validateOpeningDateTime(openingDateTime);
        validateTotalValue(totalValue);
        validateEmployee(employee);
        validateIsInternal(isInternal);
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

    private void validateEmployee(Employee employee) {
        if (employee == null) {
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

    public Boolean getInternal() {
        return isInternal;
    }

    public void changeTotalValue(Double newTotalValue) {
        validateTotalValue(newTotalValue);
        this.totalValue = newTotalValue;
    }

    public Client getClient() {
        return client;
    }

    public void changeClient(Client newClient) {
        this.client = newClient;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void changeEmployee(Employee newEmployee) {
        validateEmployee(newEmployee);
        this.employee = newEmployee;
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
        this.isInternal = (this.client == null && this.employee != null);
    }
}
