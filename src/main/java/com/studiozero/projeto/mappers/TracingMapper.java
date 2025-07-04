package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.TracingRequestDTO;
import com.studiozero.projeto.dtos.response.TracingResponseDTO;
import com.studiozero.projeto.entities.Tracing;

import java.util.List;

public class TracingMapper {

    public static Tracing toEntity(TracingRequestDTO dto) {
        Tracing tracing = new Tracing();
        tracing.setContext(dto.getContext());
        return tracing;
    }

    public static TracingResponseDTO toDTO(Tracing tracing) {
        if (tracing == null) {
            return null;
        }
        TracingResponseDTO dto = new TracingResponseDTO();
        dto.setId(tracing.getId());
        dto.setContext(tracing.getContext());
        dto.setDateTime(tracing.getDateTime());
        return dto;
    }

    public static List<TracingResponseDTO> toListDtos(List<Tracing> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TracingMapper::toDTO)
                .toList();
    }
}
