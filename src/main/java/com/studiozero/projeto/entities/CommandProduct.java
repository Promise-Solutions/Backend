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

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_produto")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_comanda", nullable = false)
    private Command command;

    @Column(name = "qtd_produto", nullable = false)
    private Integer productQuantity;

    @Column(name = "valor_unitario", nullable = false)
    private Double unitValue;

    public void addQuantity(Integer quantity) {
        setProductQuantity(this.productQuantity + quantity);
    }
}
