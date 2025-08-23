package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.JobType;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.SubJob;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "servico_ou_pacotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @Column(name = "id_servico", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_cliente")
    private ClientEntity client;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "valor_total", nullable = false)
    private Double totalValue;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobCategory category;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tipo_servico", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType serviceType;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubJobEntity> subJobs;

}
