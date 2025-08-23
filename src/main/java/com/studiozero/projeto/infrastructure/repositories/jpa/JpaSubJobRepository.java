package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSubJobRepository extends JpaRepository<SubJobEntity, UUID> {
}
