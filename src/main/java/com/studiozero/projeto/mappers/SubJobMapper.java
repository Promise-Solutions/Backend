package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.entities.SubJob;
import org.springframework.stereotype.Component;

@Component
public class SubJobMapper {

    public SubJob toEntity(SubJobRequestDTO dto) {
        SubJob subJob = new SubJob();
        subJob.setTitle(dto.getTitle());
        subJob.setDescription(dto.getDescription());
        subJob.setValue(dto.getValue());
        subJob.setDate(dto.getDate());
        subJob.setStartTime(dto.getStartTime());
        subJob.setEndTime(dto.getEndTime());
        subJob.setStatus(dto.getStatus());
        subJob.setFkService(dto.getFkService());
        return subJob;
    }

    public SubJobResponseDTO toDTO(SubJob subjob) {
        return new SubJobResponseDTO(subjob);
    }
}
