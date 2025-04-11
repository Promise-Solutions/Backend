package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.dtos.response.JobResponseDTO;
import com.studiozero.projeto.entities.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequestDTO dto) {
        Job job = new Job();
        job.setFkClient(dto.getFkClient());
        job.setTotalValue(dto.getTotalValue());
        job.setCategory(dto.getCategory());
        job.setStatus(dto.getStatus());
        job.setServiceType(dto.getServiceType());

        return job;
    }

    public JobResponseDTO toDTO(Job job) {
        return new JobResponseDTO(job);
    }

}

