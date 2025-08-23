package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface JpaCommandRepository extends JpaRepository<CommandEntity, Integer> {
    @Query("SELECT c FROM CommandEntity c WHERE c.status = :status")
    List<CommandEntity> findAllByStatus(@Param("status") Status status);

    @Query("SELECT c FROM CommandEntity c WHERE c.client.id = :clientId AND c.status = :status")
    List<CommandEntity> findAllByClientIdAndStatus(@Param("clientId") UUID clientId,
            @Param("status") Status status);

}
