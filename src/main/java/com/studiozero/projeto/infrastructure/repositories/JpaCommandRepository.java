package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface JpaCommandRepository extends JpaRepository<Command, Integer> {
    @Query("SELECT c FROM Command c WHERE c.status = :status")
    List<Command> findAllByStatus(@Param("status") Status status);

    @Query("SELECT c FROM Command c WHERE c.client.id = :clientId AND c.status = :status")
    List<Command> findAllByClientIdAndStatus(@Param("clientId") java.util.UUID clientId,
            @Param("status") Status status);
}
