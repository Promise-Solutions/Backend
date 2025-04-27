package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.JobRequestDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
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
            return jobRepository.save(job);
        } else {
        throw new NotFoundException("Job not found");
        }
    }

    public void deleteJob(UUID id) {
        if (subJobRepository.existsByJob_Id(id)) {
            throw new BadRequestException("Cannot delete job with associated sub-jobs");
        }

        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        } else {
            throw new NotFoundException("Job not found");
        }
    }

    public List<Job> listJobsByFkClient(UUID fkClient) {
        return jobRepository.findAllByClient_Id(fkClient);
    }

    public Status evaluateJobStatus(UUID jobId) {
        Integer totalSubJobs = subJobRepository.countByJob_Id(jobId);
        if(totalSubJobs == 0) {
            return Status.PENDING;
        }
        Integer totalClosed = subJobRepository.countByJob_IdAndStatus(jobId, Status.CLOSED);

        boolean allClosed = totalClosed.equals(totalSubJobs);
        if(allClosed) {
            return updateJobStatus(jobId, Status.CLOSED);
        } else if(totalClosed > 0) {
            return updateJobStatus(jobId, Status.WORKING);
        } else {
            return updateJobStatus(jobId, Status.PENDING);
        }
    }

    private Status updateJobStatus(UUID jobId, Status newStatus) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found to evaluate"));

        if(job.getStatus() != newStatus) {
            job.setStatus(newStatus);
            jobRepository.save(job);
        }
        return newStatus;
    }

    public Double calculateTotalValue(UUID jobId) {
        List<SubJob> allSubJobsByJobId = subJobRepository.findAllByJob_Id(jobId);
        if (allSubJobsByJobId.isEmpty()) {
            return 0.0;
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found"));

        Double prevTotalValue = job.getTotalValue();
        job.setTotalValue(0.0);
        job.setTotalValue(allSubJobsByJobId.stream()
                .mapToDouble(SubJob::getValue)
                .sum());

        if (!job.getTotalValue().equals(prevTotalValue)) {
            jobRepository.save(job);
        }
        return job.getTotalValue();
    }
}
