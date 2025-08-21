package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaSubJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public class SubJobRepositoryImpl implements SubJobRepository {
    private final JpaSubJobRepository jpaSubJobRepository;

    @Autowired
    public SubJobRepositoryImpl(JpaSubJobRepository jpaSubJobRepository) {
        this.jpaSubJobRepository = jpaSubJobRepository;
    }

    @Override
    public boolean existsByJobId(UUID jobId) {
        // Implementação customizada necessária
        return jpaSubJobRepository.findAll().stream()
                .anyMatch(sj -> sj.getJob() != null && jobId.equals(sj.getJob().getId()));
    }

    @Override
    public List<SubJob> findAll() {
        return jpaSubJobRepository.findAll();
    }

    @Override
    public List<SubJob> findAllByJobId(UUID fkService) {
        // Implementação customizada necessária
        return jpaSubJobRepository.findAll().stream()
                .filter(sj -> sj.getJob() != null && fkService.equals(sj.getJob().getId())).toList();
    }

    @Override
    public Integer countByJobId(UUID jobId) {
        return (int) jpaSubJobRepository.findAll().stream()
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
        return jpaSubJobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SubJob subJob) {
        jpaSubJobRepository.save(subJob);
    }

    @Override
    public void deleteById(UUID id) {
        jpaSubJobRepository.deleteById(id);
    }

    @Override
    public LocalDate findMaxDate() {
        return jpaSubJobRepository.findAll().stream().map(SubJob::getDate).max(LocalDate::compareTo).orElse(null);
    }
}
