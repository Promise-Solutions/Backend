package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SubJobRepository extends JpaRepository<SubJob, UUID>, JpaSpecificationExecutor<SubJob> {
    boolean existsByJob_Id(UUID jobId);
    List<SubJob> findAllByJob_Id(UUID fkService);
    Integer countByJob_Id(UUID jobId);
    Integer countByJob_IdAndStatus(UUID jobId, Status status);

    @Query("""
            SELECT CASE 
            WHEN COUNT(subJob) > 0 THEN TRUE 
            ELSE FALSE 
            END
            FROM SubJob AS subJob 
            JOIN subJob.job As job
            WHERE subJob.needsRoom = TRUE
            AND subJob.date = :date
            AND job.category = :category 
            AND (
                subJob.expectedEndTime > :startTime 
                AND subJob.startTime < :expectedEndTime
            )
            
    """)
    Boolean existsRoomConflict(
            @Param("category") JobCategory category,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("expectedEndTime") LocalTime expectedEndTime
    );

    @Query("""
            SELECT CASE 
            WHEN COUNT(subJob) > 0 THEN TRUE 
            ELSE FALSE 
            END
            FROM SubJob AS subJob
            JOIN subJob.job As job
            WHERE subJob.needsRoom = TRUE
            AND subJob.date = :date
            AND subJob.id <> :subJobId
            AND job.category = :category
            AND (
                subJob.expectedEndTime > :startTime 
                AND subJob.startTime < :expectedEndTime
            )    
    """)
    Boolean existsRoomConflict(
            @Param("category") JobCategory category,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("expectedEndTime") LocalTime expectedEndTime,
            @Param("subJobId") UUID jobId
    );

    @Query("SELECT MAX(s.date) FROM SubJob s")
    LocalDate findMaxDate();
}
