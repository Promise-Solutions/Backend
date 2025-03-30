package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.domain.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SubServico")
public class SubJob {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idSubServico", nullable = false)
    private UUID id;

    @Column(name = "tituloSubServico", nullable = false)
    private String title;

    @Column(name = "descricaoSubServico")
    private String description;

    @Column(name = "valorSubServico", nullable = false)
    private Double value;

    @Column(name = "data", nullable = false)
    private LocalDateTime date;

    @Column(name = "horaInicio", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "horaFim")
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "servico", nullable = false)
    private Job service;
}
