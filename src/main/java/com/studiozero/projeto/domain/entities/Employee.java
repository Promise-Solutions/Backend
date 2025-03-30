package com.studiozero.projeto.domain.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "Funcionario")
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idFuncionario", nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tel", nullable = false)
    private String phone;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "senha", nullable = false)
    private String password;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "ativo", nullable = false)
    private Boolean active;
}
