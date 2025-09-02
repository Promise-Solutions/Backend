package com.studiozero.projeto.operations.infrastruture.repository.jpa;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.infrastruture.entity.JobEntity;
import com.studiozero.projeto.operations.appllication.enums.JobCategory;
import com.studiozero.projeto.shared.application.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaJobRepository extends JpaRepository<JobEntity, UUID> {
    @Query("SELECT SUM(j.totalValue) FROM JobEntity j WHERE j.category = :category AND j.status = :status")
    Double sumTotalValueByCategoryAndStatus(@Param("category") JobCategory category, @Param("status") Status status);

    Job findBySubJobs_Id(UUID subJobId);
}
