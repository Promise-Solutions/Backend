package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {
    boolean existsByFkClient(UUID fkClient);
}
