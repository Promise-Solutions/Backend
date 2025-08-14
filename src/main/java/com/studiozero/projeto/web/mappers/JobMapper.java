package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.dtos.request.JobRequestDTO;
import com.studiozero.projeto.application.dtos.response.JobResponseDTO;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JobMapper {

    private final ClientRepository clientRepository;

    public JobMapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Job toEntity(JobRequestDTO dto) {
        Client client = clientRepository.findById(dto.getFkClient())
                .orElseThrow(() -> new NotFoundException("FkClient not found"));

        Job job = new Job();
        job.setClient(client);
        job.setTitle(dto.getTitle());
        job.setTotalValue(dto.getTotalValue());
        job.setCategory(dto.getCategory());
        job.setStatus(dto.getStatus());
        job.setServiceType(dto.getServiceType());

        return job;
    }

    public static JobResponseDTO toDTO(Job job) {
        if (job == null) {
            return null;
        }
        JobResponseDTO dto = new JobResponseDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setCategory(job.getCategory());
        dto.setServiceType(job.getServiceType());
        dto.setTotalValue(job.getTotalValue());
        dto.setFkClient(job.getClient() != null ? job.getClient().getId() : null);
        dto.setStatus(job.getStatus());
        return dto;
    }

    public static List<JobResponseDTO> toListDtos(List<Job> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(JobMapper::toDTO)
                .toList();
    }

    public Job toEntity(JobRequestDTO dto, UUID id) {
        if (dto == null) return null;

        Client client = clientRepository.findById(dto.getFkClient())
                .orElseThrow(() -> new NotFoundException("FkClient not found!"));

        Job job = new Job();

        job.setId(id);
        job.setTitle(dto.getTitle());
        job.setCategory(dto.getCategory());
        job.setServiceType(dto.getServiceType());
        job.setTotalValue(dto.getTotalValue());
        job.setClient(client);
        job.setStatus(dto.getStatus());

        return job;
    }

}

