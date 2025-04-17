package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.SubJobMapper;
import com.studiozero.projeto.repositories.JobRepository;
import com.studiozero.projeto.repositories.SubJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubJobService {

    private final SubJobRepository subJobRepository;
    private final JobRepository jobRepository;
    private final SubJobMapper subJobMapper;

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

    public void deleteSubJob(UUID id) {
        if (subJobRepository.existsById(id)) {
            subJobRepository.deleteById(id);
        }
        throw new NotFoundException("SubJob not found");
    }
}
