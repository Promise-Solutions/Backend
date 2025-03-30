package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.domain.enums.JobCategory;
import com.studiozero.projeto.domain.enums.JobType;
import com.studiozero.projeto.domain.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "ServicoOuPacotes")
public class Job {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idServico", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Client client;

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
