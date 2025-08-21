package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.application.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface JpaClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByCpf(String cpf);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.active = true AND c.clientType = :clientType")
    long countByActiveTrueAndClientType(@Param("clientType") ClientType clientType);
}
