package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.JobRepository;
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
        Job job = jobRepository.findById(dto.getFkService())
                .orElseThrow(() -> new NotFoundException("FkJob not found"));

        SubJob subJob = new SubJob();
        subJob.setTitle(dto.getTitle());
        subJob.setDescription(dto.getDescription());
        subJob.setValue(dto.getValue());
        subJob.setDate(dto.getDate());
        subJob.setStartTime(dto.getStartTime());
        subJob.setEndTime(dto.getEndTime());
        subJob.setStatus(dto.getStatus());
        subJob.setJob(job);
        return subJob;
    }

    public static SubJobResponseDTO toDTO(SubJob subjob) {
        return new SubJobResponseDTO(subjob);
    }

    public static List<SubJobResponseDTO> toListDtos(List<SubJob> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(SubJobMapper::toDTO)
                .toList();
    }

    public SubJob toEntity (SubJobRequestDTO dto, UUID id) {
        if (dto == null) return null;

        Job job = jobRepository.findById(dto.getFkService())
                .orElseThrow(() -> new NotFoundException("FkJob not found!"));

        SubJob subjob = new SubJob();

        subjob.setId(id);
        subjob.setJob(job);
        subjob.setTitle(dto.getTitle());
        subjob.setDescription(dto.getDescription());
        subjob.setDate(dto.getDate());
        subjob.setStartTime(dto.getStartTime());
        subjob.setEndTime(dto.getEndTime());
        subjob.setValue(dto.getValue());
        subjob.setStatus(dto.getStatus());

        return subjob;
    }
}
