package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "servico_ou_pacotes")
public class JobEntity {

    @Id
    @Column(name = "id_servico", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_cliente")
    private ClientEntity client;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "valor_total", nullable = false)
    private Double totalValue;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobCategory category;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tipo_servico", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType serviceType;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubJobEntity> subJobs;

    public JobEntity() {
    }

    public JobEntity(UUID id, ClientEntity client, String title, Double totalValue, JobCategory category, Status status, JobType serviceType, List<SubJobEntity> subJobs) {
        this.id = id;
        this.client = client;
        this.title = title;
        this.totalValue = totalValue;
        this.category = category;
        this.status = status;
        this.serviceType = serviceType;
        this.subJobs = subJobs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public JobCategory getCategory() {
        return category;
    }

    public void setCategory(JobCategory category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobType getServiceType() {
        return serviceType;
    }

    public void setServiceType(JobType serviceType) {
        this.serviceType = serviceType;
    }

    public List<SubJobEntity> getSubJobs() {
        return subJobs;
    }

    public void setSubJobs(List<SubJobEntity> subJobs) {
        this.subJobs = subJobs;
    }
}
