package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.tracing.CreateTracingUseCase;
import com.studiozero.projeto.application.usecases.tracing.DeleteAllTracingsUseCase;
import com.studiozero.projeto.application.usecases.tracing.ListTracingsUseCase;
import com.studiozero.projeto.domain.entities.Tracing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TracingEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateTracingUseCase create = new CreateTracingUseCase(null) {
            @Override
            public Tracing execute(Tracing tracing) { return tracing; }
        };

        ListTracingsUseCase list = new ListTracingsUseCase(null) {
            @Override
            public java.util.List<Tracing> execute() { return List.of(); }
        };

        DeleteAllTracingsUseCase deleteAll = new DeleteAllTracingsUseCase(null) {
            @Override
            public void execute() { }
        };

        TracingController controller = new TracingController(create, list, deleteAll);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllTracings() throws Exception {
        mockMvc.perform(get("/api/tracing"))
                .andExpect(status().isNoContent());
    }
}
