package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.web.dtos.request.JobRequestDTO;
import com.studiozero.projeto.web.dtos.response.JobResponseDTO;
import com.studiozero.projeto.web.handlers.NotFoundException;
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
        if (dto == null)
            return null;
        Client client = clientRepository.findById(dto.getFkClient());
        if (client == null)
            throw new NotFoundException("FkClient not found");
        // Geração de id pode ser feita no use case, aqui passamos null
        return new Job(
                null,
                dto.getTitle(),
                dto.getTotalValue(),
                dto.getCategory(),
                dto.getStatus(),
                client,
                dto.getServiceType());
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
        if (dto == null || id == null)
            return null;
        Client client = clientRepository.findById(dto.getFkClient());
        if (client == null)
            throw new NotFoundException("FkClient not found!");
        return new Job(
                id,
                dto.getTitle(),
                dto.getTotalValue(),
                dto.getCategory(),
                dto.getStatus(),
                client,
                dto.getServiceType());
    }

}
