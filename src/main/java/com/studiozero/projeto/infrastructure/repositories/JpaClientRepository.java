package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import com.studiozero.projeto.application.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface JpaClientRepository extends JpaRepository<ClientEntity, UUID> {
    boolean existsByCpf(String cpf);

    @Query("SELECT COUNT(c) FROM ClientEntity c WHERE c.active = true AND c.clientType = :clientType")
    long countByActiveTrueAndClientType(@Param("clientType") ClientType clientType);
}
