package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tarefa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

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
    private Employee employee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne (optional = true)
    @JoinColumn(name = "fk_autor")
    private Employee assign;
}
