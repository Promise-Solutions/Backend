package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.applications.web.dtos.request.JobCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.JobDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.JobReadRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.JobUpdateRequestDTO;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.exceptions.EntityNotFoundException;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job save(JobCreateRequestDTO jobDto){
        return jobRepository.save(
                new Job(
                        jobDto.getStatus(),
                        jobDto.getCategory()
                )
        );
    }

    @Override
    public List<Job> searchAll() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> read(JobReadRequestDTO jobDto) {
        Optional<Job> jobOpt = jobRepository.findById(jobDto.getId());
        if (jobOpt.isEmpty()) {
            throw new EntityNotFoundException("Job not found for search");
        }
        return jobOpt;
    }

    @Override
    public Job update(JobUpdateRequestDTO jobDto) {
        Optional<Job> jobOpt = jobRepository.findById(jobDto.getId());
        if (jobOpt.isEmpty()) {
            throw new EntityNotFoundException("Job not found for update");
        }
        Job user = jobOpt.get();
        return jobRepository.save(
                new Job(
                        user.getId(),
                        jobDto.getCategory(),
                        jobDto.getStatus()
                )
        );
    }

    @Override
    public Optional<Job> delete(JobDeleteRequestDTO jobDto) {
        Optional<Job> jobOpt = jobRepository.findById(jobDto.getId());
        if (jobOpt.isEmpty()) {
            throw new EntityNotFoundException("Job not found for delete");
        }
        jobRepository.delete(jobOpt.get());
        return jobOpt;
    }
}
