package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Comanda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda", nullable = false)
    private Integer id;

    @Column(name = "dataHoraAbertura", nullable = false)
    private LocalDateTime openingDateTime;

    @Column(name = "dataHoraFechamento")
    private LocalDateTime closingDateTime;

    @Column(name = "desconto")
    private Double discount;

    @Column(name = "valorTotal", nullable = false)
    private Double totalValue;

    @Column(name = "fkCliente")
    private UUID fkClient;

    @Column(name = "fkFuncionario", nullable = false)
    private UUID fkEmployee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
