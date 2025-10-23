package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaSubJobRepository extends JpaRepository<SubJobEntity, UUID> {
    @Query("SELECT s.job FROM SubJobEntity s WHERE s.id = :subJobId")
    JobEntity findJobBySubJobId(@Param("subJobId") UUID subJobId);
    Page<SubJobEntity> findAllByJob(JobEntity job, Pageable pageable);
    List<SubJobEntity> findAllByDate(LocalDate todayDate);
}
