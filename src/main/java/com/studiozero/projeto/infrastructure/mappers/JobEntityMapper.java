package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobEntityMapper {
    public static Job toDomain(JobEntity entity) {
        if (entity == null) return null;

        Job job = new Job(
                entity.getId(),
                entity.getTitle(),
                entity.getTotalValue(),
                entity.getCategory(),
                entity.getStatus(),
                ClientEntityMapper.toDomain(entity.getClient()),
                entity.getServiceType()
        );
        List<SubJob> subJobsDomain = SubJobEntityMapper.toDomainList(entity.getSubJobs(), job);
        job.setSubJobs(subJobsDomain);

        return job;
    }

    public static JobEntity toEntity(Job job) {
        if (job == null) return null;
        ClientEntity clientEntity = ClientEntityMapper.toEntity(job.getClient());

        JobEntity jobEntity = new JobEntity(
                job.getId(),
                clientEntity,
                job.getTitle(),
                job.getTotalValue(),
                job.getCategory(),
                job.getStatus(),
                job.getServiceType()
        );

        List<SubJobEntity> subJobs =  SubJobEntityMapper.toEntityList(job.getSubJobs(), jobEntity);
        jobEntity.setSubJobs(subJobs);

        return jobEntity;
    }

    public static List<Job> toDomainList(List<JobEntity> entities) {
        if (entities == null) return null;
        if (entities.isEmpty()) return new ArrayList<>();

        return entities.stream().map(JobEntityMapper::toDomain).toList();
    }

    public static List<JobEntity> toEntityList(List<Job> jobs) {
        if (jobs == null) return null;
        if (jobs.isEmpty()) return new ArrayList<>();

        return jobs.stream().map(JobEntityMapper::toEntity).toList();
    }
}
