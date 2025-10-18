package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListSubJobsUseCase {
    private final SubJobRepository subJobRepository;

    public ListSubJobsUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public Page<SubJob> execute(Pageable pageable) {
        return subJobRepository.findAll(pageable);
    }
}
