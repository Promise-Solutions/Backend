package com.studiozero.projeto.customers.infrastruture.repository;

import com.studiozero.projeto.customers.application.ClientType;
import com.studiozero.projeto.customers.infrastruture.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, UUID> {
    boolean existsByCpf(String cpf);

    @Query("SELECT COUNT(c) FROM ClientEntity c WHERE c.active = true AND c.clientType = :clientType")
    long countByActiveTrueAndClientType(@Param("clientType") ClientType clientType);
}
