package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaJobRepository extends JpaRepository<JobEntity, UUID> {
    @Query("SELECT SUM(j.totalValue) FROM JobEntity j WHERE j.category = :category AND j.status = :status")
    Double sumTotalValueByCategoryAndStatus(@Param("category") JobCategory category, @Param("status") Status status);
}
