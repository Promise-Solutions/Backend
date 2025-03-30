package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.domain.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Tarefa")
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idTarefa", nullable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "data_inicio")
    private LocalDateTime startDate;

    @Column(name = "data_limite")
    private LocalDateTime limitDate;

    @ManyToOne
    @JoinColumn(name = "responsavel", nullable = false)
    private Employee responsible;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
