package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.dtos.response.JobResponseDTO;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.exceptions.BadRequestException;
import com.studiozero.projeto.exceptions.EntityNotFoundException;
import com.studiozero.projeto.mappers.JobMapper;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SubJobRepository subJobRepository;

    public JobResponseDTO save(JobRequestDTO jobDto) {
        Job job = jobMapper.toEntity(jobDto);
        Job savedJob = jobRepository.save(job);

        return jobMapper.toDTO(savedJob);
    }

    public JobResponseDTO findById(UUID id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        return jobMapper.toDTO(job);
    }

    public List<JobResponseDTO> findAll() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    public JobResponseDTO update(UUID id, JobRequestDTO jobDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        job.setFkClient(jobDto.getFkClient());
        job.setTotalValue(jobDto.getTotalValue());
        job.setCategory(jobDto.getCategory());
        job.setStatus(jobDto.getStatus());
        job.setServiceType(jobDto.getServiceType());

        Job updatedJob = jobRepository.save(job);

        return jobMapper.toDTO(updatedJob);
    }

    public String delete(UUID id) {
        if (subJobRepository.existsByFkService(id)) {
            throw new BadRequestException("Cannot delete job with associated sub-jobs");
        }
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        jobRepository.delete(job);
        return "Job deleted successfully";
    }
}
