package com.studiozero.projeto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private Integer id;

    @Column(name = "nome_produto", nullable = false)
    private String name;

    @Column(name = "qtd_produto", nullable = false)
    private Integer quantity;

    @Column(name = "valor_unitario", nullable = false)
    private Double unitValue;
}