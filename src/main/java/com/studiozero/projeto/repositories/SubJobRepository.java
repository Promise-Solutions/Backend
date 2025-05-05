package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubJobRepository extends JpaRepository<SubJob, UUID>, JpaSpecificationExecutor<SubJob> {
    boolean existsByJob_Id(UUID jobId);
    List<SubJob> findAllByJob_Id(UUID fkService);
    Integer countByJob_Id(UUID jobId);
    Integer countByJob_IdAndStatus(UUID jobId, Status status);
    Integer countAllByStatus(Status status);
}
