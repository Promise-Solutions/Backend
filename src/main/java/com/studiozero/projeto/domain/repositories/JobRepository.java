package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID>, JpaSpecificationExecutor<Job> {
    List<Job> findAllByClient_Id(UUID fkClient);

    @Query("SELECT SUM(j.totalValue) FROM Job j WHERE j.category = :category AND j.status = :status")
    Double sumTotalValueByCategoryAndStatus(@Param("category") JobCategory category, @Param("status") Status status);

}
