package com.studiozero.projeto.infrastructure.entities;

import com.studiozero.projeto.application.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comanda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda", nullable = false)
    private Integer id;

    @Column(name = "numero_comanda", nullable = false)
    private Integer commandNumber;

    @Column(name = "data_hora_abertura", nullable = false)
    private LocalDateTime openingDateTime;

    @Column(name = "data_hora_fechamento")
    private LocalDateTime closingDateTime;

    @Column(name = "desconto")
    private Double discount;

    @Column(name = "valor_total", nullable = false)
    private Double totalValue;

    @ManyToOne(optional = true)
    @JoinColumn(name = "fk_cliente")
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_funcionario")
    private EmployeeEntity employee;

    @Column(name = "comanda_interna", nullable = false)
    private Boolean isInternal;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
