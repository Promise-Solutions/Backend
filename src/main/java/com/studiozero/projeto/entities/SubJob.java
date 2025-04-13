package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SubServico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSubServico", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tituloSubServico", nullable = false)
    private String title;

    @Column(name = "descricaoSubServico")
    private String description;

    @Column(name = "valorSubServico", nullable = false)
    private Double value;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Column(name = "horaInicio", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "horaFim")
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "fkServico", nullable = false)
    private UUID fkService;
}
