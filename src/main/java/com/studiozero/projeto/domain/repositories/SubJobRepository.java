package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.SubJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubJobRepository extends JpaRepository<SubJob, UUID> {}
