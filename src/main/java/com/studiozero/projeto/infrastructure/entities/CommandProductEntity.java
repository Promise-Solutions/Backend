package com.studiozero.projeto.infrastructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comanda_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda_produto", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_produto")
    private ProductEntity product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_comanda", nullable = false)
    private CommandEntity command;

    @Column(name = "qtd_produto", nullable = false)
    private Integer productQuantity;

    @Column(name = "valor_unitario", nullable = false)
    private Double unitValue;

}
