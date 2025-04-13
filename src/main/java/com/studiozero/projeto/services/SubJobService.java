package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.SubJobRequestDTO;
import com.studiozero.projeto.dtos.response.SubJobResponseDTO;
import com.studiozero.projeto.entities.SubJob;
import com.studiozero.projeto.exceptions.EntityNotFoundException;
import com.studiozero.projeto.mappers.SubJobMapper;
import com.studiozero.projeto.repositories.SubJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubJobService {

    @Autowired
    private SubJobRepository subJobRepository;

    @Autowired
    private SubJobMapper subJobMapper;

    public SubJobResponseDTO save(SubJobRequestDTO subJobDto) {
        SubJob subJob = subJobMapper.toEntity(subJobDto);
        SubJob savedSubJob = subJobRepository.save(subJob);

        return subJobMapper.toDTO(savedSubJob);
    }

    public SubJobResponseDTO findById(UUID id) {
        SubJob subJob = subJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub job not found"));
        return subJobMapper.toDTO(subJob);
    }

    public List<SubJobResponseDTO> findAll() {
        return subJobRepository.findAll().stream()
                .map(subJobMapper::toDTO)
                .toList();
    }

    public SubJobResponseDTO update(UUID id, SubJobRequestDTO jobDto) {
        SubJob subJob = subJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub job not found"));

        subJob.setTitle(jobDto.getTitle());
        subJob.setDescription(jobDto.getDescription());
        subJob.setValue(jobDto.getValue());
        subJob.setDate(jobDto.getDate());
        subJob.setStartTime(jobDto.getStartTime());
        subJob.setEndTime(jobDto.getEndTime());
        subJob.setStatus(jobDto.getStatus());
        subJob.setFkService(jobDto.getFkService());

        SubJob updatedSubJob = subJobRepository.save(subJob);

        return subJobMapper.toDTO(updatedSubJob);
    }

    public void delete(UUID id) {
        SubJob subJob = subJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubJob not found"));
        subJobRepository.delete(subJob);
    }
}
