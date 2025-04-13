package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.ClientType;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Alterado para GenerationType.UUID
    @Column(name = "id_cliente", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contato", nullable = false)
    private String contact;

    @Column(name = "tipoCliente", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @Column(name = "ativo", nullable = false)
    private Boolean active;
}