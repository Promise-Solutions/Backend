package com.studiozero.projeto.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ComandaProduto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComandaProduto", nullable = false)
    private Integer id;

    @Column(name = "fkProduto", nullable = false)
    private Integer fkProduct;

    @Column(name = "fkComanda", nullable = false)
    private Integer fkCommand;

    @Column(name = "qtdProduto", nullable = false)
    private Integer productQuantity;

    @Column(name = "valorUnitario", nullable = false)
    private Double unitValue;
}
