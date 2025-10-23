package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ListSubJobsByFkServiceUseCase {
    private final SubJobRepository subJobRepository;

    public ListSubJobsByFkServiceUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public Page<SubJob> execute(UUID fkService, Pageable pageable) {
        return subJobRepository.findAllByJob(fkService, pageable);
    }
}
