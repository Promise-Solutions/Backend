package com.studiozero.projeto.application.usecases.subjob;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class ListTodaySubJobsUseCase {
    private final SubJobRepository subJobRepository;

    public ListTodaySubJobsUseCase(SubJobRepository subJobRepository) {
        this.subJobRepository = subJobRepository;
    }

    @Transactional(readOnly = true)
    public List<SubJob> execute() {
        LocalDate todayDate = LocalDate.now();
        return subJobRepository.findByTodayDate(todayDate);
    }
}
