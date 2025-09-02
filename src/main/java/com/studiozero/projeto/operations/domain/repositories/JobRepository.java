package com.studiozero.projeto.operations.domain.repositories;

import com.studiozero.projeto.operations.domain.entities.Job;
import com.studiozero.projeto.operations.appllication.enums.JobCategory;
import com.studiozero.projeto.shared.application.enums.Status;
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
