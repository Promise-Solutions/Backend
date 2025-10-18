package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.mappers.JobEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Job> findAll(Pageable pageable) {
        return jpaJobRepository.findAll(pageable)
                .map(JobEntityMapper::toDomain);
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
