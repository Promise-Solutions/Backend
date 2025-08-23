package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JobEntityMapper {
    public static Job toDomain(JobEntity entity) {
        if (entity == null) return null;
        return new Job(
            entity.getId(),
            entity.getTitle(),
            entity.getTotalValue(),
            entity.getCategory(),
            entity.getStatus(),
            ClientEntityMapper.toDomain(entity.getClient()),
            entity.getServiceType()
        );
    }

    public static JobEntity toEntity(Job job) {
        if (job == null) return null;
        ClientEntity clientEntity = ClientEntityMapper.toEntity(job.getClient());
        return new JobEntity(
            job.getId(),
            clientEntity,
            job.getTitle(),
            job.getTotalValue(),
            job.getCategory(),
            job.getStatus(),
            job.getServiceType(),
            null);
    }

    public static List<Job> toDomainList(List<JobEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(JobEntityMapper::toDomain).toList();
    }

    public static List<JobEntity> toEntityList(List<Job> jobs) {
        if (jobs == null) return null;
        return jobs.stream().map(JobEntityMapper::toEntity).toList();
    }
}
