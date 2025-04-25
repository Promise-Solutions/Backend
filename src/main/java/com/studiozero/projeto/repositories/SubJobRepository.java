package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubJobRepository extends JpaRepository<SubJob, UUID>, JpaSpecificationExecutor<SubJob> {
    boolean existsByJob_Id(UUID jobId);
}
