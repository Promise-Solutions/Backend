package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaSubJobRepository extends JpaRepository<SubJobEntity, UUID> {
}
