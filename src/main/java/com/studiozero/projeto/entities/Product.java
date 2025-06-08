package com.studiozero.projeto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@Builder
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

    @Column(name = "valor_cliente", nullable = false)
    private Double clientValue;

    @Column(name = "valor_interno", nullable = false)
    private Double internalValue;

    // alterado com a mudança da lógica do sistema
    //@Column(name = "despesa_compra", nullable = false)
    //private Double totalBuyValue;
}