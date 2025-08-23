package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.List;
import java.util.UUID;

public class ListSubJobsByFkServiceUseCase {
    private final SubJobRepository subJobRepository;

    public ListSubJobsByFkServiceUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    public List<SubJob> execute(UUID fkService) {
        return subJobRepository.findAllByJobId(fkService);
    }
}
