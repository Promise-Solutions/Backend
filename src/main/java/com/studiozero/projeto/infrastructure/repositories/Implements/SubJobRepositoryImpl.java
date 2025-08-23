package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaSubJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import com.studiozero.projeto.infrastructure.mappers.SubJobEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SubJobRepositoryImpl implements SubJobRepository {
    private final JpaSubJobRepository jpaSubJobRepository;

    @Override
    public boolean existsByJobId(UUID jobId) {
        return jpaSubJobRepository.findAll().stream()
                .anyMatch(sj -> sj.getJob() != null && jobId.equals(sj.getJob().getId()));
    }

    @Override
    public List<SubJob> findAll() {
        return jpaSubJobRepository.findAll().stream()
            .map(SubJobEntityMapper::toDomain)
            .toList();
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
        SubJobEntity entity = SubJobEntityMapper.toEntity(subJob);
        jpaSubJobRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaSubJobRepository.deleteById(id);
    }

    @Override
    public LocalDate findMaxDate() {
        return jpaSubJobRepository.findAll().stream().map(SubJobEntity::getDate).max(LocalDate::compareTo).orElse(null);
    }
}
