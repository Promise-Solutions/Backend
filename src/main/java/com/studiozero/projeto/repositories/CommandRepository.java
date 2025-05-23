package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer>, JpaSpecificationExecutor<Command> {

    boolean existsByClient_Id(UUID clientId);

    List<Command> findAllByStatus(Status status);

    @Query("SELECT c FROM Command c WHERE c.client.id = :clientId AND c.status = :status")
    List<Command> findAllByClient_IdAndStatus(UUID clientId, Status status);

    @Query("SELECT MAX(c.openingDateTime) FROM Command c")
    LocalDateTime findMaxOpeningDate();

    @Query("SELECT MAX(c.closingDateTime) FROM Command c")
    LocalDateTime findMaxClosingDate();
}
