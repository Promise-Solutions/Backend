package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.SubJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaSubJobRepository extends JpaRepository<SubJob, UUID> {
}
