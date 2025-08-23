package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.List;

public class ListSubJobsUseCase {
    private final SubJobRepository subJobRepository;

    public ListSubJobsUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public List<SubJob> execute() {
        return subJobRepository.findAll();
    }
}
