package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;

import java.util.UUID;

public class JobEntity {
    private final UUID id;
    private String title;
    private Double totalValue;
    private JobCategory category;
    private Status status;
    private ClientEntity clientEntity;
    private JobType serviceType;

    public JobEntity(UUID id, String title, Double totalValue, JobCategory category, Status status, ClientEntity clientEntity,
                     JobType serviceType) {
        if (id == null || id.toString().isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        validateTitle(title);
        validateTotalValue(totalValue);
        validateCategory(category);
        validateStatus(status);
        validateClient(clientEntity);
        validateServiceType(serviceType);
        this.id = id;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.clientEntity = clientEntity;
        this.serviceType = serviceType;
    }

    private void validateClient(ClientEntity clientEntity) {
        if (clientEntity == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
    }

    private void validateServiceType(JobType serviceType) {
        if (serviceType == null) {
            throw new IllegalArgumentException("ServiceType cannot be null");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (title.length() < 2 || title.length() > 100) {
            throw new IllegalArgumentException("Title must be between 2 and 100 characters");
        }
    }

    private void validateTotalValue(Double value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero");
        }
    }

    private void validateCategory(JobCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }

    private void validateStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public JobCategory getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public ClientEntity getClient() {
        return clientEntity;
    }

    public JobType getServiceType() {
        return serviceType;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    public void changeTotalValue(Double newValue) {
        validateTotalValue(newValue);
        this.totalValue = newValue;
    }

    public void changeCategory(JobCategory newCategory) {
        validateCategory(newCategory);
        this.category = newCategory;
    }

    public void changeStatus(Status newStatus) {
        validateStatus(newStatus);
        this.status = newStatus;
    }

    public void changeClient(ClientEntity newClientEntity) {
        validateClient(newClientEntity);
        this.clientEntity = newClientEntity;
    }

    public void changeServiceType(JobType newServiceType) {
        validateServiceType(newServiceType);
        this.serviceType = newServiceType;
    }
}
