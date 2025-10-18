package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.mappers.JobEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import com.studiozero.projeto.infrastructure.mappers.SubJobEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class SubJobRepositoryImpl implements SubJobRepository {
    private final JpaSubJobRepository jpaSubJobRepository;
    private final JpaJobRepository jpaJobRepository;

    public SubJobRepositoryImpl(JpaSubJobRepository jpaSubJobRepository, JpaJobRepository jobRepository) {
        this.jpaSubJobRepository = jpaSubJobRepository;
        this.jpaJobRepository = jobRepository;
    }

    @Override
    public boolean existsByJobId(UUID jobId) {
        return jpaSubJobRepository.findAll().stream()
                .anyMatch(sj -> sj.getJob() != null && jobId.equals(sj.getJob().getId()));
    }

    @Override
    public Page<SubJob> findAll(Pageable pageable) {
        return jpaSubJobRepository.findAll(pageable)
                .map(SubJobEntityMapper::toDomain);
    }

    @Override
    public List<SubJob> findAllByJobId(UUID fkService) {
        return jpaSubJobRepository.findAll().stream()
            .map(SubJobEntityMapper::toDomain)
            .filter(sj -> sj.getJob() != null && fkService.equals(sj.getJob().getId())).toList();
    }

    @Override
    public Integer countByJobId(UUID jobId) {
        return (int) jpaSubJobRepository.findAll().stream()
            .map(SubJobEntityMapper::toDomain)
            .filter(sj -> sj.getJob() != null && jobId.equals(sj.getJob().getId())).count();
    }

    @Override
    public Integer countByJobIdAndStatus(UUID jobId, Status status) {
        return (int) jpaSubJobRepository.findAll().stream()
                .filter(sj -> sj.getJob() != null && jobId.equals(sj.getJob().getId()) && status.equals(sj.getStatus()))
                .count();
    }

    @Override
    public Boolean existsRoomConflict(JobCategory category, LocalDate date, LocalTime startTime,
            LocalTime expectedEndTime) {
        // Implementação simplificada, ajuste conforme lógica de negócio
        return jpaSubJobRepository.findAll().stream().anyMatch(sj -> sj.getNeedsRoom() != null && sj.getNeedsRoom()
                && sj.getDate() != null && sj.getDate().equals(date)
                && sj.getJob() != null && category.equals(sj.getJob().getCategory())
                && sj.getExpectedEndTime() != null && sj.getStartTime() != null
                && sj.getExpectedEndTime().isAfter(startTime)
                && sj.getStartTime().isBefore(expectedEndTime));
    }

    @Override
    public Boolean existsRoomConflictExceptId(JobCategory category, LocalDate date, LocalTime startTime,
            LocalTime expectedEndTime, UUID subJobId) {
        return jpaSubJobRepository.findAll().stream().anyMatch(sj -> !sj.getId().equals(subJobId)
                && sj.getNeedsRoom() != null && sj.getNeedsRoom()
                && sj.getDate() != null && sj.getDate().equals(date)
                && sj.getJob() != null && category.equals(sj.getJob().getCategory())
                && sj.getExpectedEndTime() != null && sj.getStartTime() != null
                && sj.getExpectedEndTime().isAfter(startTime)
                && sj.getStartTime().isBefore(expectedEndTime));
    }

    @Override
    public SubJob findById(UUID id) {
        return jpaSubJobRepository.findById(id)
            .map(SubJobEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(SubJob subJob) {
        Job job = subJob.getJob();
        JobEntity jobEntity = JobEntityMapper.toEntity(job);

        jpaJobRepository.save(jobEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaSubJobRepository.deleteById(id);
    }

    @Override
    public LocalDate findMaxDate() {
        return jpaSubJobRepository.findAll().stream().map(SubJobEntity::getDate).max(LocalDate::compareTo).orElse(null);
    }

    @Override
    public Job findJobBySubJobId(UUID subJobId) {
        return JobEntityMapper.toDomain(jpaSubJobRepository.findJobBySubJobId(subJobId));
    }

    @Override
    public List<SubJob> findByTodayDate(LocalDate todayDate) {
        List<SubJobEntity> subJobsFound = jpaSubJobRepository.findAllByDate(todayDate);
        return SubJobEntityMapper.toDomainList(subJobsFound);
    }
}
