package com.studiozero.projeto.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "Produto")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idProduto", nullable = false)
    private UUID id;

    @Column(name = "nomeProduto", nullable = false)
    private String name;

    @Column(name = "qtdProduto", nullable = false)
    private Integer quantity;

    @Column(name = "valorUnitario", nullable = false)
    private Double unitValue;
}