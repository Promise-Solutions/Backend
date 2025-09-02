package com.studiozero.projeto.operations.appllication.usecases.subjob;

import com.studiozero.projeto.operations.domain.entities.SubJob;
import com.studiozero.projeto.operations.domain.repositories.SubJobRepository;
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
