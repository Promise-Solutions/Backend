package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.dtos.response.JobResponseDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.ClientRepository;
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
        job.setTotalValue(dto.getTotalValue());
        job.setCategory(dto.getCategory());
        job.setStatus(dto.getStatus());
        job.setServiceType(dto.getServiceType());

        return job;
    }

    public static JobResponseDTO toDTO(Job job) {
        return new JobResponseDTO(job);
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

