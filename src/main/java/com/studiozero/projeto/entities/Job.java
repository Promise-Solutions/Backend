package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.enums.JobType;
import com.studiozero.projeto.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "ServicoOuPacotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idServico", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "fkCliente", nullable = false)
    private UUID fkClient;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "valorTotal", nullable = false)
    private Double totalValue;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobCategory category;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tipoServico", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType serviceType;
}
