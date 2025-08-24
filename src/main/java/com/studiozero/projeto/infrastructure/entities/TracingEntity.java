package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Context;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rastreio")
public class TracingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rastreio", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "contexto", nullable = false)
    @Enumerated(EnumType.STRING)
    private Context context;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dateTime;

    public TracingEntity() {
    }

    public TracingEntity(Integer id, Context context, LocalDateTime dateTime) {
        this.id = id;
        this.context = context;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
