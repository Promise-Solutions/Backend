package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.mappers.JobEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class JobRepositoryImpl implements JobRepository {
    private final JpaJobRepository jpaJobRepository;

    @Override
    public List<Job> findAll() {
        return jpaJobRepository.findAll()
            .stream()
            .map(JobEntityMapper::toDomain)
            .toList();
    }

    @Override
    public List<Job> findAllByClientId(UUID clientId) {
        return jpaJobRepository.findAll().stream()
            .map(JobEntityMapper::toDomain)
            .filter(j -> j.getClient() != null && clientId.equals(j.getClient().getId())).toList();
    }

    @Override
    public Double sumTotalValueByCategoryAndStatus(JobCategory category, Status status) {
        return jpaJobRepository.sumTotalValueByCategoryAndStatus(category, status);
    }

    @Override
    public Job findById(UUID id) {
        return jpaJobRepository.findById(id)
            .map(JobEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(Job job) {
        JobEntity entity = JobEntityMapper.toEntity(job);
        jpaJobRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaJobRepository.deleteById(id);
    }
}
