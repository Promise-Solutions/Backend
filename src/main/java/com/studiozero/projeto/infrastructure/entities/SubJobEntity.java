package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "sub_servico")
public class SubJobEntity {

    @Id
    @Column(name = "id_sub_servico", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "titulo_sub_servico", nullable = false)
    private String title;

    @Column(name = "descricao_sub_servico")
    private String description;

    @Column(name = "valor_sub_servico", nullable = false)
    private Double value;

    @Column(name = "data")
    private LocalDate date;

    @Column(name = "hora_inicio")
    private LocalTime startTime;

    @Column(name = "hora_fim")
    private LocalTime expectedEndTime;

    @Column(name = "necessita_sala", nullable = false)
    private Boolean needsRoom;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_servico")
    private JobEntity job;

    public SubJobEntity() {
    }

    public SubJobEntity(UUID id, String title, String description, Double value, LocalDate date, LocalTime startTime, LocalTime expectedEndTime, Boolean needsRoom, Status status, JobEntity job) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.value = value;
        this.date = date;
        this.startTime = startTime;
        this.expectedEndTime = expectedEndTime;
        this.needsRoom = needsRoom;
        this.status = status;
        this.job = job;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(LocalTime expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public Boolean getNeedsRoom() {
        return needsRoom;
    }

    public void setNeedsRoom(Boolean needsRoom) {
        this.needsRoom = needsRoom;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }
}
