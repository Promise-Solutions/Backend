package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comanda")
public class CommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda", nullable = false)
    private Integer id;

    @Column(name = "numero_comanda", nullable = false)
    private Integer commandNumber;

    @Column(name = "data_hora_abertura", nullable = false)
    private LocalDateTime openingDateTime;

    @Column(name = "data_hora_fechamento")
    private LocalDateTime closingDateTime;

    @Column(name = "desconto")
    private Double discount;

    @Column(name = "valor_total", nullable = false)
    private Double totalValue;

    @ManyToOne(optional = true)
    @JoinColumn(name = "fk_cliente")
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_funcionario")
    private EmployeeEntity employee;

    @Column(name = "comanda_interna", nullable = false)
    private Boolean isInternal;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public CommandEntity() {
    }

    public CommandEntity(Integer id, Integer commandNumber, LocalDateTime openingDateTime, LocalDateTime closingDateTime, Double discount, Double totalValue, ClientEntity client, EmployeeEntity employee, Boolean isInternal, Status status) {
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

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
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

    public LocalDateTime getClosingDateTime() {
        return closingDateTime;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }
}
