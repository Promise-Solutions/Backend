package com.studiozero.projeto.operations.infrastruture.repository.impl;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.domain.repositories.JobRepository;
import com.studiozero.projeto.operations.infrastruture.repository.jpa.JpaJobRepository;
import com.studiozero.projeto.operations.appllication.enums.JobCategory;
import com.studiozero.projeto.shared.application.enums.Status;
import com.studiozero.projeto.operations.infrastruture.entity.JobEntity;
import com.studiozero.projeto.operations.infrastruture.mapper.JobEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JobRepositoryImpl implements JobRepository {
    private final JpaJobRepository jpaJobRepository;

    public JobRepositoryImpl(JpaJobRepository jpaJobRepository) {
        this.jpaJobRepository = jpaJobRepository;
    }

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
