package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "sub_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
