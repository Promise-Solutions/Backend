package com.studiozero.projeto.infrastructure.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class ProductEntity {

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

    public ProductEntity() {
    }

    public ProductEntity(Integer id, String name, Integer quantity, Double clientValue, Double internalValue) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.clientValue = clientValue;
        this.internalValue = internalValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getClientValue() {
        return clientValue;
    }

    public void setClientValue(Double clientValue) {
        this.clientValue = clientValue;
    }

    public Double getInternalValue() {
        return internalValue;
    }

    public void setInternalValue(Double internalValue) {
        this.internalValue = internalValue;
    }
}