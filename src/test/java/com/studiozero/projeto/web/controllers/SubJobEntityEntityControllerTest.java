package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.job.GetJobUseCase;
import com.studiozero.projeto.application.usecases.subjob.*;
import com.studiozero.projeto.domain.entities.Job;
import com.studiozero.projeto.domain.entities.SubJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubJobEntityEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateSubJobUseCase create = new CreateSubJobUseCase(null) {
            @Override
            public SubJob execute(SubJob subJob) { return subJob; }
        };

        GetSubJobUseCase get = new GetSubJobUseCase(null) {
            @Override
            public SubJob execute(UUID id) { throw new IllegalArgumentException("not found"); }
        };

        UpdateSubJobUseCase update = new UpdateSubJobUseCase(null) {
            @Override
            public SubJob execute(SubJob subJob) { return subJob; }
        };

        DeleteSubJobUseCase delete = new DeleteSubJobUseCase(null) {
            @Override
            public SubJob execute(UUID id) { return null; }
        };

        ListSubJobsByFkServiceUseCase listByFk = new ListSubJobsByFkServiceUseCase(null) {
            @Override
            public org.springframework.data.domain.Page<SubJob> execute(UUID fkService, org.springframework.data.domain.Pageable pageable) { return new PageImpl<>(List.of()); }
        };

        ListSubJobsUseCase listAll = new ListSubJobsUseCase(null) {
            @Override
            public java.util.List<SubJob> execute() { return List.of(); }
        };

        UpdateSubJobStatusUseCase updateStatus = new UpdateSubJobStatusUseCase(null) {
            @Override
            public SubJob execute(UUID id, Enum status, UUID jobId) { return null; }
        };

        GetJobUseCase getJob = new GetJobUseCase(null) {
            @Override
            public Job execute(UUID id) { return null; }
        };

        SubJobController controller = new SubJobController(create, get, update, delete, listByFk, listAll, updateStatus, getJob);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllSubJobs() throws Exception {
        mockMvc.perform(get("/api/sub-jobs"))
                .andExpect(status().isNoContent());
    }
}
