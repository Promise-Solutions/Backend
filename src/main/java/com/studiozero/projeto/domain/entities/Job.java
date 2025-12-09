package com.studiozero.projeto.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.application.enums.JobType;
import jakarta.persistence.EntityNotFoundException;

public class Job {
    private UUID id;
    private String title;
    private Double totalValue;
    private JobCategory category;
    private Status status;
    private Client client;
    private JobType serviceType;
    private List<SubJob> subJobs;

    public Job(UUID id, String title, Double totalValue, JobCategory category, Status status, Client client,
            JobType serviceType, List<SubJob> subJobs) {
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
        this.subJobs = new ArrayList<>(subJobs);
    }

    public Job(String title, Double totalValue, JobCategory category, Status status, Client client,
            JobType serviceType, List<SubJob> subJobs) {
        validateTitle(title);
        validateTotalValue(totalValue);
        validateCategory(category);
        validateStatus(status);
        validateClient(client);
        validateServiceType(serviceType);
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.client = client;
        this.serviceType = serviceType;
        this.subJobs = new ArrayList<>(subJobs);
    }

    public Job(String title, Double totalValue, JobCategory category, Status status, Client client,
            JobType serviceType) {
        validateTitle(title);
        validateTotalValue(totalValue);
        validateCategory(category);
        validateStatus(status);
        validateClient(client);
        validateServiceType(serviceType);
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.client = client;
        this.serviceType = serviceType;
        this.subJobs = new ArrayList<>();
    }

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
        this.subJobs = new ArrayList<>();
    }

    public Job() {

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
        if (value == null) {
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

    public List<SubJob> getSubJobs() {
        return subJobs;
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

    public void setSubJobs(List<SubJob> subJobs) {
        this.subJobs = new ArrayList<>(subJobs);
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

    public void addSubJob(SubJob subJob) {
        if(subJob == null) {
            throw new IllegalArgumentException("Sub Job passado nulo, não é possível o adicionar ao subJob " + this.title);
        }

        subJob.changeJob(this);
        this.subJobs.add(subJob);

        evaluateStatus();
        calculateTotalValue();
    }


    public void evaluateStatus() {
        int totalSubJobs = subJobs.size();

        if(totalSubJobs == 0) {
            this.status = Status.PENDING;
            return;
        }

        long totalSubJobsClosed = subJobs.stream()
                .filter(s -> s.getStatus() == Status.CLOSED)
                .count();
        boolean allSubJobsClosed = totalSubJobs == totalSubJobsClosed;

        Status newStatus = Status.PENDING;

        if(allSubJobsClosed) {
            newStatus = Status.CLOSED;
        } else if(totalSubJobsClosed > 0) {
            newStatus = Status.WORKING;
        }

        if(this.status != newStatus) {
            changeStatus(newStatus);
        }
    }

    public void calculateTotalValue() {
        if (this.subJobs.isEmpty()) {
            changeTotalValue(0.0);
            return;
        }

        Double prevTotalValue = this.totalValue;

        Double newTotalValue = 0.0;
        for (SubJob currentSubJob : subJobs) {
            newTotalValue += currentSubJob.getValue();
        }

        boolean jobTotalValueEqual = newTotalValue.equals(prevTotalValue);

        if (!jobTotalValueEqual) {
            changeTotalValue(newTotalValue);
        }
    }

    public void updateSubJob(SubJob subJob) {
        SubJob subJobFound = this.subJobs
                .stream()
                .filter(s -> s.getId().equals(subJob.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("subserviço não encontrado, não é possível realizar a atualização"));

        subJobFound.update(subJob);
        calculateTotalValue();
    }

    public SubJob removeSubJob(UUID subJobId) {
        SubJob subJobDeleted = null;
        for (SubJob currentSubJob : subJobs) {
            if(currentSubJob.getId().equals(subJobId)) {
                subJobDeleted = currentSubJob;
                subJobs.remove(currentSubJob);
                break;
            }
        }

        if(subJobDeleted == null) {
            throw new EntityNotFoundException("Sub Job não encontrado para remover");
        }

        evaluateStatus();
        calculateTotalValue();
        return subJobDeleted;
    }
}
