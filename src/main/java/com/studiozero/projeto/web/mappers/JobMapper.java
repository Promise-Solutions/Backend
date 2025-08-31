package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.web.dtos.request.JobRequestDTO;
import com.studiozero.projeto.web.dtos.response.JobResponseDTO;
import com.studiozero.projeto.web.dtos.response.SubJobResponseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class JobMapper {
    public static Job toDomain(JobRequestDTO dto, Client client) {
        if (dto == null || client == null) return null;

        return new Job(
            dto.getTitle(),
            dto.getTotalValue(),
            dto.getCategory(),
            dto.getStatus(),
            client,
            dto.getServiceType()
        );
    }

    public static Job toDomain(JobRequestDTO dto, UUID id, Client client) {
        if (dto == null || id == null || client == null) return null;
        return new Job(
            id,
            dto.getTitle(),
            dto.getTotalValue(),
            dto.getCategory(),
            dto.getStatus(),
            client,
            dto.getServiceType()
        );
    }

    public static JobResponseDTO toDTO(Job job) {
        if (job == null) return null;
        JobResponseDTO dto = new JobResponseDTO();
        List<SubJobResponseDTO> subJobsDto = SubJobMapper.toDTOList(job.getSubJobs());

        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setCategory(job.getCategory());
        dto.setServiceType(job.getServiceType());
        dto.setTotalValue(job.getTotalValue());
        dto.setFkClient(job.getClient() != null ? job.getClient().getId() : null);
        dto.setStatus(job.getStatus());
        dto.setSubJobs(subJobsDto);
        return dto;
    }

    public static List<JobResponseDTO> toDTOList(List<Job> entities) {
        if (entities == null) return null;
        if (entities.isEmpty()) return new ArrayList<>();

        return entities.stream().map(JobMapper::toDTO).toList();
    }
}
