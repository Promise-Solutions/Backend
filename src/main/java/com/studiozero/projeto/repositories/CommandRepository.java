package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {
    boolean existsByClient_Id(UUID clientId);
    List<Command> findAllByStatus(Status status);
}
