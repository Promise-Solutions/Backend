package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.job.CreateJobUseCase;
import com.studiozero.projeto.application.usecases.job.DeleteJobUseCase;
import com.studiozero.projeto.application.usecases.job.GetJobUseCase;
import com.studiozero.projeto.application.usecases.job.ListJobsUseCase;
import com.studiozero.projeto.application.usecases.job.UpdateJobUseCase;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateJobUseCase create = new CreateJobUseCase(null) {
            @Override
            public void execute(Job job) { /* no-op */ }
        };

        GetJobUseCase getJob = new GetJobUseCase(null) {
            @Override
            public Job execute(UUID id) { throw new IllegalArgumentException("not found"); }
        };

        UpdateJobUseCase update = new UpdateJobUseCase(null) {
            @Override
            public void execute(Job job) { /* no-op */ }
        };

        DeleteJobUseCase delete = new DeleteJobUseCase(null) {
            @Override
            public void execute(UUID id) { }
        };

        ListJobsUseCase list = new ListJobsUseCase(null) {
            @Override
            public org.springframework.data.domain.Page<Job> execute(org.springframework.data.domain.Pageable pageable) {
                return new PageImpl<>(List.of());
            }

            @Override
            public List<Job> executeByClient(UUID fkClient) { return List.of(); }
        };

        GetClientUseCase getClient = new GetClientUseCase(null) {
            @Override
            public Client execute(UUID id) { return null; }
        };

        JobController controller = new JobController(create, getJob, update, delete, list, getClient);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAllJobs() throws Exception {
        mockMvc.perform(get("/api/jobs").param("page", "0").param("size", "10"))
                .andExpect(status().isNoContent());
    }
}
