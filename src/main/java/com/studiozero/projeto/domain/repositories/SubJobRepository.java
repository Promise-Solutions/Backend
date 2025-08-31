package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface SubJobRepository {
        boolean existsByJobId(UUID jobId);

        List<SubJob> findAll();

        List<SubJob> findAllByJobId(UUID fkService);

        Integer countByJobId(UUID jobId);

        Integer countByJobIdAndStatus(UUID jobId, Status status);

        Boolean existsRoomConflict(JobCategory category, LocalDate date, LocalTime startTime,
                        LocalTime expectedEndTime);

        Boolean existsRoomConflictExceptId(JobCategory category, LocalDate date, LocalTime startTime,
                        LocalTime expectedEndTime, UUID subJobId);

        SubJob findById(UUID id);

        void save(SubJob subJob);

        void deleteById(UUID id);

        LocalDate findMaxDate();

        Job findJobBySubJobId(UUID subJobId);
}
