package com.studiozero.projeto.operations.infrastruture.repository.jpa;

import com.studiozero.projeto.operations.infrastruture.entity.JobEntity;
import com.studiozero.projeto.operations.infrastruture.entity.SubJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSubJobRepository extends JpaRepository<SubJobEntity, UUID> {
    @Query("SELECT s.job FROM SubJobEntity s WHERE s.id = :subJobId")
    JobEntity findJobBySubJobId(@Param("subJobId") UUID subJobId);
}
