package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.application.enums.JobCategory;
import com.studiozero.projeto.application.enums.Status;
import java.util.UUID;

import java.util.List;

public interface JobRepository {
    List<Job> findAll();

    List<Job> findAllByClientId(UUID clientId);

    Double sumTotalValueByCategoryAndStatus(JobCategory category, Status status);

    Job findById(UUID id);

    void save(Job job);

    void deleteById(UUID id);
}
