package com.studiozero.projeto.entities;

import com.studiozero.projeto.enums.ClientType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_cliente", nullable = false)
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