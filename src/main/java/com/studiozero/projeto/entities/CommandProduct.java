package com.studiozero.projeto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comanda_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda_produto", nullable = false)
    private Integer id;

    @Column(name = "fk_produto", nullable = false)
    private Integer fkProduct;

    @Column(name = "fk_comanda", nullable = false)
    private Integer fkCommand;

    @Column(name = "qtd_produto", nullable = false)
    private Integer productQuantity;

    @Column(name = "valor_unitario", nullable = false)
    private Double unitValue;
}
