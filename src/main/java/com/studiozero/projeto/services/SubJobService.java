package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.response.SubJobDeleteResponseDTO;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
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
    private final JobService jobService;
    private final TracingService tracingService;

    public SubJob createSubJob(SubJob subJob) {
        if(
           subJob.getNeedsRoom()
            &&
           subJobRepository.existsRoomConflict(
                   subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(), subJob.getExpectedEndTime())
            ) {
            throw new ConflictException("There is a room usage conflict");
        }

        subJob.setId(UUID.randomUUID());
        tracingService.setTracing(Context.JOB);
        return subJobRepository.save(subJob);
    }

    public SubJob findSubJobById(UUID id) {
        return subJobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sub job not found"));
    }

    public List<SubJob> listSubJobs() {
        return subJobRepository.findAll();
    }

    public SubJob updateSubJob(SubJob subJob) {
        if (subJobRepository.existsById(subJob.getId())) {
            subJob.setId(subJob.getId());

            if(
                subJob.getNeedsRoom()
                &&
                subJobRepository.existsRoomConflict(
                        subJob.getJob().getCategory(), subJob.getDate(), subJob.getStartTime(), subJob.getExpectedEndTime(), subJob.getId())
            ) {
                throw new ConflictException("There is a room usage conflict");
            }
            tracingService.setTracing(Context.JOB);
            return subJobRepository.save(subJob);
        }
        throw new NotFoundException("Sub job not found");
    }

    @Transactional
    public SubJob updateSubJobStatus(UUID id, Status status) {
        SubJob subJob = subJobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sub job not found"));

        subJob.setStatus(status);
        tracingService.setTracing(Context.JOB);
        return subJobRepository.save(subJob);
    }

    public List<SubJob> listSubJobsByFkService(UUID fkService) {
        return subJobRepository.findAllByJob_Id(fkService);
    }

    public SubJobDeleteResponseDTO deleteSubJob(UUID subJobId) {
        Optional<SubJob> subJobOptional = subJobRepository.findById(subJobId);

        if (subJobOptional.isPresent()) {
            SubJob subJob = subJobOptional.get();
            tracingService.setTracing(Context.JOB);
            subJobRepository.deleteById(subJobId);
            Status jobStatus = jobService.evaluateJobStatus(subJob.getJob().getId());
            Double totalValueJob = jobService.calculateTotalValue(subJob.getJob());

            return new SubJobDeleteResponseDTO(subJobId, jobStatus, totalValueJob);
        } else {
            throw new NotFoundException("SubJob not found");
        }
    }
}
