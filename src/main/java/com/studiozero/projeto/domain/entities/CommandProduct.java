package com.studiozero.projeto.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "ComandaProduto")
public class CommandProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idComandaProduto", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "comanda", nullable = false)
    private Command command;

    @Column(name = "qtdProduto", nullable = false)
    private Integer productQuantity;

    @Column(name = "valorUnitario", nullable = false)
    private Double unitValue;
}
