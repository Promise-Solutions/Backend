package com.studiozero.projeto.domain.entities;

import com.studiozero.projeto.domain.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Comanda")
public class Command {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idComanda", nullable = false)
    private UUID id;

    @Column(name = "dataHoraAbertura", nullable = false)
    private LocalDateTime openingDateTime;

    @Column(name = "dataHoraFechamento")
    private LocalDateTime closingDateTime;

    @Column(name = "desconto")
    private Double discount;

    @Column(name = "valorTotal", nullable = false)
    private Double totalValue;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "funcionario", nullable = false)
    private Employee employee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
