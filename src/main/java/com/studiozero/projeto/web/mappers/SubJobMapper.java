package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.web.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.web.dtos.response.SubJobResponseDTO;
import java.util.List;
import java.util.UUID;

public class SubJobMapper {
    public static SubJob toDomain(SubJobRequestDTO dto, Job job) {
        if (dto == null || job == null) return null;
        return new SubJob(
            null,
            dto.getTitle(),
            dto.getDescription(),
            dto.getValue(),
            dto.getDate(),
            dto.getStartTime(),
            dto.getExpectedEndTime(),
            dto.getNeedsRoom(),
            dto.getStatus(),
            job
        );
    }

    public static SubJob toDomain(SubJobRequestDTO dto, UUID id, Job job) {
        if (dto == null || job == null) return null;
        return new SubJob(
            id,
            dto.getTitle(),
            dto.getDescription(),
            dto.getValue(),
            dto.getDate(),
            dto.getStartTime(),
            dto.getExpectedEndTime(),
            dto.getNeedsRoom(),
            dto.getStatus(),
            job
        );
    }

    public static SubJobResponseDTO toDTO(SubJob subJob) {
        if (subJob == null) return null;
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setNeedsRoom(subJob.getNeedsRoom());
        dto.setStatus(subJob.getStatus());
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        return dto;
    }

    public static SubJobResponseDTO toDTO(SubJob subJob, Status jobStatus, Double totalValueJob) {
        if (subJob == null) return null;
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setNeedsRoom(subJob.getNeedsRoom());
        dto.setStatus(jobStatus);
        dto.setJobTotalValue(totalValueJob);
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        return dto;
    }

    public static SubJobResponseDTO toDTO(SubJob subJob, Double totalValueJob) {
        if (subJob == null) return null;
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setNeedsRoom(subJob.getNeedsRoom());
        dto.setStatus(subJob.getStatus());
        dto.setJobTotalValue(totalValueJob);
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        return dto;
    }

    public static List<SubJobResponseDTO> toDTOList(List<SubJob> entities) {
        if (entities == null) return null;
        return entities.stream().map(SubJobMapper::toDTO).toList();
    }
}
