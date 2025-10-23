package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.infrastructure.entities.JobEntity;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubJobEntityMapper {

    public static SubJob toDomain(SubJobEntity entity) {
        if (entity == null) return null;
        Job job = JobEntityMapper.toDomain(entity.getJob());
        return new SubJob(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getValue(),
            entity.getDate(),
            entity.getStartTime(),
            entity.getExpectedEndTime(),
            entity.getNeedsRoom(),
            entity.getStatus(),
            job
        );
    }

    public static SubJob toDomain(SubJobEntity entity, Job job) {
        if (entity == null) return null;
        return new SubJob(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getValue(),
            entity.getDate(),
            entity.getStartTime(),
            entity.getExpectedEndTime(),
            entity.getNeedsRoom(),
            entity.getStatus(),
            job
        );
    }

    public static SubJobEntity toEntity(SubJob subJob, JobEntity job) {
        if (subJob == null) return null;
        return new SubJobEntity(
            subJob.getId(),
            subJob.getTitle(),
            subJob.getDescription(),
            subJob.getValue(),
            subJob.getDate(),
            subJob.getStartTime(),
            subJob.getExpectedEndTime(),
            subJob.getNeedsRoom(),
            subJob.getStatus(),
            job
        );
    }

    public static List<SubJob> toDomainList(List<SubJobEntity> entities, Job job) {
        if (entities == null) return null;
        if (entities.isEmpty()) return new ArrayList<>();

        return entities.stream().map(s -> toDomain(s, job)).toList();
    }

    public static List<SubJob> toDomainList(List<SubJobEntity> entities) {
        if (entities == null) return null;
        if (entities.isEmpty()) return new ArrayList<>();

        return entities.stream().map(SubJobEntityMapper::toDomain).toList();
    }

    public static List<SubJobEntity> toEntityList(List<SubJob> subJobs, JobEntity job) {
        if (subJobs == null) return null;
        if (subJobs.isEmpty()) return new ArrayList<>();

        return subJobs.stream().map(s -> toEntity(s, job)).toList();
    }

}
