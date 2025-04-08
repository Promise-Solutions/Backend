package com.studiozero.projeto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto", nullable = false)
    private Integer id;

    @Column(name = "nomeProduto", nullable = false)
    private String name;

    @Column(name = "qtdProduto", nullable = false)
    private Integer quantity;

    @Column(name = "valorUnitario", nullable = false)
    private Double unitValue;
}