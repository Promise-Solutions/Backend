package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "tarefa")
public class TaskEntity {

    @Id
    @Column(name = "id_tarefa", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "data_inicio")
    private LocalDate startDate;

    @Column(name = "data_limite")
    private LocalDate limitDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "fk_funcionario")
    private EmployeeEntity employee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne (optional = true)
    @JoinColumn(name = "fk_autor")
    private EmployeeEntity assign;

    public TaskEntity() {
    }

    public TaskEntity(UUID id, String title, String description, LocalDate startDate, LocalDate limitDate, EmployeeEntity employee, Status status, EmployeeEntity assign) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.employee = employee;
        this.status = status;
        this.assign = assign;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public EmployeeEntity getAssign() {
        return assign;
    }

    public void setAssign(EmployeeEntity assign) {
        this.assign = assign;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
