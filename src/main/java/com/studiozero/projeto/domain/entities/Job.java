package com.studiozero.projeto.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atendimento")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categoria", length = 50, nullable = false)
    private String category;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Job() {
    }

    public Job(String category, String status) {
        this.category = category;
        this.status = status;
    }

    public Job(Integer id, String category, String status) {
        this.id = id;
        this.category = category;
        this.status = status;
    }

    public Job(Integer id, String category, String status, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
