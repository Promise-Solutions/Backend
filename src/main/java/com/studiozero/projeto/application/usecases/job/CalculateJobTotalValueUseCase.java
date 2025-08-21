package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.List;

public class CalculateJobTotalValueUseCase {
    private final JobRepository jobRepository;
    private final SubJobRepository subJobRepository;

    public CalculateJobTotalValueUseCase(JobRepository jobRepository, SubJobRepository subJobRepository) {
        this.jobRepository = jobRepository;
        this.subJobRepository = subJobRepository;
    }

    public Double execute(Job job) {
        List<SubJob> allSubJobsByJobId = subJobRepository.findAllByJobId(job.getId());
        if (allSubJobsByJobId.isEmpty()) {
            job.changeTotalValue(0.0);
            jobRepository.save(job);
        }
        Double prevTotalValue = job.getTotalValue();
        job.changeTotalValue(0.0);
        job.changeTotalValue(allSubJobsByJobId.stream()
                .mapToDouble(SubJob::getValue)
                .sum());
        if (!job.getTotalValue().equals(prevTotalValue)) {
            jobRepository.save(job);
        }
        return job.getTotalValue();
    }
}
