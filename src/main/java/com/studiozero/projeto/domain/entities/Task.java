package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Tarefa")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate startDate;

    @Column(name = "data_limite")
    private LocalDate limitDate;

    @Column(name = "fkFuncionario", nullable = false)
    private UUID fkEmployee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
