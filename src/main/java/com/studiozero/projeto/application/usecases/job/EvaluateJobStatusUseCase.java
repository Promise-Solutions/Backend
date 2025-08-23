package com.studiozero.projeto.application.usecases.job;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.repositories.SubJobRepository;
import java.util.UUID;

public class EvaluateJobStatusUseCase {
    private final JobRepository jobRepository;
    private final SubJobRepository subJobRepository;

    public EvaluateJobStatusUseCase(JobRepository jobRepository, SubJobRepository subJobRepository) {
        this.jobRepository = jobRepository;
        this.subJobRepository = subJobRepository;
    }

    public Status execute(UUID jobId) {
        Integer totalSubJobs = subJobRepository.countByJobId(jobId);
        if (totalSubJobs == 0) {
            return Status.PENDING;
        }
        Integer totalClosed = subJobRepository.countByJobIdAndStatus(jobId, Status.CLOSED);
        boolean allClosed = totalClosed.equals(totalSubJobs);
        if (allClosed) {
            return Status.CLOSED;
        } else if (totalClosed > 0) {
            return Status.WORKING;
        } else {
            return Status.PENDING;
        }
    }
}
