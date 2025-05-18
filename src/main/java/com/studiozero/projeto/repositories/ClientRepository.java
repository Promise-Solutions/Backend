package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByCpf(String cpf);
    double countByActiveTrueAndClientType(ClientType clientType);
}