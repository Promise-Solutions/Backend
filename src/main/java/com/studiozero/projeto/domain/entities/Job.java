package com.studiozero.projeto.domain.entities;

import java.util.UUID;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.application.enums.JobType;

public class Job {
    private UUID id;
    private String title;
    private Double totalValue;
    private JobCategory category;
    private Status status;
    private Client client;
    private JobType serviceType;

    public Job(UUID id, String title, Double totalValue, JobCategory category, Status status, Client client,
            JobType serviceType) {
        validateTitle(title);
        validateTotalValue(totalValue);
        validateCategory(category);
        validateStatus(status);
        validateClient(client);
        validateServiceType(serviceType);
        this.id = id;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.client = client;
        this.serviceType = serviceType;
    }

    private void validateClient(Client client) {
        if (client == null) {
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

    public Client getClient() {
        return client;
    }

    public JobType getServiceType() {
        return serviceType;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void setCategory(JobCategory category) {
        this.category = category;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setServiceType(JobType serviceType) {
        this.serviceType = serviceType;
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

    public void changeClient(Client newClient) {
        validateClient(newClient);
        this.client = newClient;
    }

    public void changeServiceType(JobType newServiceType) {
        validateServiceType(newServiceType);
        this.serviceType = newServiceType;
    }
}
