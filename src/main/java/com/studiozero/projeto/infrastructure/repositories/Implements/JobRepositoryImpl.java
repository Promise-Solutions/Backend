package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaJobRepository;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JobRepositoryImpl implements JobRepository {
    private final JpaJobRepository jpaJobRepository;

    @Autowired
    public JobRepositoryImpl(JpaJobRepository jpaJobRepository) {
        this.jpaJobRepository = jpaJobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jpaJobRepository.findAll();
    }

    @Override
    public List<Job> findAllByClientId(UUID clientId) {
        return jpaJobRepository.findAll().stream()
                .filter(j -> j.getClient() != null && clientId.equals(j.getClient().getId())).toList();
    }

    @Override
    public Double sumTotalValueByCategoryAndStatus(JobCategory category, Status status) {
        return jpaJobRepository.sumTotalValueByCategoryAndStatus(category, status);
    }

    @Override
    public Job findById(UUID id) {
        return jpaJobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Job job) {
        jpaJobRepository.save(job);
    }

    @Override
    public void deleteById(UUID id) {
        jpaJobRepository.deleteById(id);
    }
}
