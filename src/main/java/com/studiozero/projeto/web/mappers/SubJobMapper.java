package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.web.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.web.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.domain.repositories.JobRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SubJobMapper {

    private final JobRepository jobRepository;

    public SubJobMapper(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public SubJob toEntity(SubJobRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Job job = jobRepository.findById(dto.getFkService());
        if (job == null) {
            return null;
        }
        return new SubJob(
                java.util.UUID.randomUUID(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getValue(),
                dto.getDate(),
                dto.getStartTime(),
                dto.getExpectedEndTime(),
                dto.getNeedsRoom(),
                dto.getStatus(),
                job);
    }

    public static SubJobResponseDTO toDTO(SubJob subJob) {
        if (subJob == null) {
            return null;
        }
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setNeedsRoom(subJob.getNeedsRoom());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setStatus(subJob.getStatus());
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        return dto;
    }

    public static SubJobResponseDTO toDTO(SubJob subJob, Double jobTotalValue) {
        if (subJob == null) {
            return null;
        }
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setNeedsRoom(subJob.getNeedsRoom());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setStatus(subJob.getStatus());
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        dto.setJobTotalValue(jobTotalValue);
        return dto;
    }

    public static SubJobResponseDTO toDTO(SubJob subJob, Status jobStatus, Double jobTotalValue) {
        if (subJob == null) {
            return null;
        }
        SubJobResponseDTO dto = new SubJobResponseDTO();
        dto.setId(subJob.getId());
        dto.setTitle(subJob.getTitle());
        dto.setDescription(subJob.getDescription());
        dto.setValue(subJob.getValue());
        dto.setDate(subJob.getDate());
        dto.setStartTime(subJob.getStartTime());
        dto.setExpectedEndTime(subJob.getExpectedEndTime());
        dto.setStatus(subJob.getStatus());
        dto.setFkService(subJob.getJob() != null ? subJob.getJob().getId() : null);
        dto.setJobStatus(jobStatus);
        dto.setJobTotalValue(jobTotalValue);
        return dto;
    }

    public static List<SubJobResponseDTO> toListDtos(List<SubJob> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(SubJobMapper::toDTO)
                .toList();
    }

    public SubJob toEntity(SubJobRequestDTO dto, UUID id) {
        if (dto == null || id == null)
            return null;

        Job job = jobRepository.findById(dto.getFkService());
        if (job == null) {
            return null;
        }
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
                job);
    }
}
