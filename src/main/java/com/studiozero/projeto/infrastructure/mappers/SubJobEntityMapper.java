package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.infrastructure.entities.SubJobEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubJobEntityMapper {
    public static SubJob toDomain(SubJobEntity entity) {
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
            JobEntityMapper.toDomain(entity.getJob())
        );
    }

    public static SubJobEntity toEntity(SubJob subJob) {
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
            JobEntityMapper.toEntity(subJob.getJob())
        );
    }

    public static List<SubJob> toDomainList(List<SubJobEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(SubJobEntityMapper::toDomain).toList();
    }

    public static List<SubJobEntity> toEntityList(List<SubJob> subJobs) {
        if (subJobs == null) return null;
        return subJobs.stream().map(SubJobEntityMapper::toEntity).toList();
    }
}
