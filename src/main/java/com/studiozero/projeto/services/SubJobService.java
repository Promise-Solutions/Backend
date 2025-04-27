package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.request.SubJobUpdateStatusRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.SubJobMapper;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubJobService {

    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;
    private final SubJobMapper subJobMapper;
    private final JobService jobService;

    public SubJob createSubJob(SubJobRequestDTO subJobdto) {
        Job job = jobRepository.findById(subJobdto.getFkService())
                .orElseThrow(() -> new NotFoundException("Job not found"));

        SubJob subjob = subJobMapper.toEntity(subJobdto);

        subjob.setId(UUID.randomUUID());
        subjob.setJob(job);
        return subJobRepository.save(subjob);
    }

    public SubJob findSubJobById(UUID id) {
        return subJobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sub job not found"));
    }

    public List<SubJob> listSubJobs() {
        return subJobRepository.findAll();
    }

    public SubJob updateSubJob(SubJob subjob) {
        if (subJobRepository.existsById(subjob.getId())) {
            subjob.setId(subjob.getId());
            return subJobRepository.save(subjob);
        }
        throw new NotFoundException("Sub job not found");
    }

    @Transactional
    public SubJob updateSubJobStatus(UUID id, SubJobUpdateStatusRequestDTO statusDTO) {
        SubJob subJob = subJobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sub job not found"));

        subJob.setStatus(statusDTO.getStatus());
        subJob.setEndTime(statusDTO.getEndTime());
        return subJobRepository.save(subJob);
    }

    public List<SubJob> listSubJobsByFkService(UUID fkService) {
        return subJobRepository.findAllByJob_Id(fkService);
    }

    public SubJobDeleteResponseDTO deleteSubJob(UUID subJobId) {
        Optional<SubJob> subJobOptional = subJobRepository.findById(subJobId);

        if (subJobOptional.isPresent()) {
            SubJob subJob = subJobOptional.get();
            subJobRepository.deleteById(subJobId);
            Status jobStatus = jobService.evaluateJobStatus(subJob.getJob().getId());
            Double totalValueJob = jobService.calculateTotalValue(subJob.getJob().getId());

            return new SubJobDeleteResponseDTO(subJobId, jobStatus, totalValueJob);
        } else {
            throw new NotFoundException("SubJob not found");
        }
    }
}
