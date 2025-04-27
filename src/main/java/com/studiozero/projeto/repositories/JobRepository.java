package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID>, JpaSpecificationExecutor<Job> {
    List<Job> findAllByClient_Id(UUID fkClient);

    @Query("""
    SELECT SUM(j.totalValue) 
    FROM Job j 
    WHERE j.status = com.studiozero.projeto.enums.Status.CLOSED
    """)
    Double getTotalClosedJobsValue();
}
