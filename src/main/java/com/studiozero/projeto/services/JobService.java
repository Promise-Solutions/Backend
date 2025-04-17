package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.exceptions.BadRequestException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.JobMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final SubJobRepository subJobRepository;
    private final JobMapper jobMapper;
    private final ClientRepository clientRepository;

    final
    public Job createJob(JobRequestDTO jobdto) {
        Client client = clientRepository.findById(jobdto.getFkClient())
                .orElseThrow(() -> new NotFoundException("Client not found!"));

        Job job = jobMapper.toEntity(jobdto);
        job.setId(UUID.randomUUID());
        job.setClient(client);
        return jobRepository.save(job);
    }

    public Job findjobById(UUID id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));
    }

    public List<Job> listJobs() {
        return jobRepository.findAll();
}

    public Job updateJob(Job job) {
        if (jobRepository.existsById(job.getId())) {
            job.setId(job.getId());
            jobRepository.save(job);
        }
        throw new NotFoundException("Job not found");
    }

    public void deleteJob(UUID id) {
        if (subJobRepository.existsByJob_Id(id)) {
            throw new BadRequestException("Cannot delete job with associated sub-jobs");
        }

        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        }
        throw new NotFoundException("Job not found");
    }
}
