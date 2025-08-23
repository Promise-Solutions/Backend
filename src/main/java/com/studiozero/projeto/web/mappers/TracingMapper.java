package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.web.dtos.request.TracingRequestDTO;
import com.studiozero.projeto.web.dtos.response.TracingResponseDTO;

import java.util.List;

public class TracingMapper {
    public static Tracing toDomain(TracingRequestDTO dto) {
        if (dto == null) return null;
        return new Tracing(null, dto.getContext(), java.time.LocalDateTime.now());
    }

    public static TracingResponseDTO toDTO(Tracing tracing) {
        if (tracing == null) return null;
        return new TracingResponseDTO(tracing.getId(), tracing.getContext(), tracing.getDateTime());
    }

    public static List<TracingResponseDTO> toDTOList(List<Tracing> entities) {
        if (entities == null) return null;
        return entities.stream().map(TracingMapper::toDTO).toList();
    }
}
