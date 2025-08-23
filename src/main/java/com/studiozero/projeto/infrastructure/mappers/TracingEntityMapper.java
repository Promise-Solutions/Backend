package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.infrastructure.entities.TracingEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TracingEntityMapper {
    public static Tracing toDomain(TracingEntity entity) {
        if (entity == null) return null;
        return new Tracing(
            entity.getId(),
            entity.getContext(),
            entity.getDateTime()
        );
    }

    public static TracingEntity toEntity(Tracing tracing) {
        if (tracing == null) return null;
        return new TracingEntity(
            tracing.getId(),
            tracing.getContext(),
            tracing.getDateTime()
        );
    }

    public static List<Tracing> toDomainList(List<TracingEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(TracingEntityMapper::toDomain).toList();
    }

    public static List<TracingEntity> toEntityList(List<Tracing> tracings) {
        if (tracings == null) return null;
        return tracings.stream().map(TracingEntityMapper::toEntity).toList();
    }
}
