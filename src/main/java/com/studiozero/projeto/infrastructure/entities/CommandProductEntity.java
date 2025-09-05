package com.studiozero.projeto.infrastructure.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "comanda_produto")
public class    CommandProductEntity {

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

    public CommandProductEntity() {
    }

    public CommandProductEntity(Integer id, ProductEntity product, CommandEntity command, Integer productQuantity, Double unitValue) {
        this.id = id;
        this.product = product;
        this.command = command;
        this.productQuantity = productQuantity;
        this.unitValue = unitValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public CommandEntity getCommand() {
        return command;
    }

    public void setCommand(CommandEntity command) {
        this.command = command;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }
}
