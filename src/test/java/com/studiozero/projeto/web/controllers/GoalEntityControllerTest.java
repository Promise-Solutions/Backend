package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.goal.CreateGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.DeleteGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetMostRecentGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.ListGoalsUseCase;
import com.studiozero.projeto.application.usecases.goal.UpdateGoalUseCase;
import com.studiozero.projeto.domain.entities.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoalEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateGoalUseCase create = new CreateGoalUseCase(null) {
            @Override
            public void execute(Goal goal) { }
        };
        GetGoalUseCase get = new GetGoalUseCase(null) {
            @Override
            public Goal execute(Integer id) { throw new IllegalArgumentException("not found"); }
        };
        ListGoalsUseCase list = new ListGoalsUseCase(null) {
            @Override
            public java.util.List<Goal> execute() { return List.of(); }
        };
        UpdateGoalUseCase update = new UpdateGoalUseCase(null) {
            @Override
            public Goal execute(Goal goal) { return goal; }
        };
        DeleteGoalUseCase delete = new DeleteGoalUseCase(null) {
            @Override
            public void execute(Integer id) { }
        };
        GetMostRecentGoalUseCase recent = new GetMostRecentGoalUseCase(null) {
            @Override
            public Goal execute() { return null; }
        };

        GoalController controller = new GoalController(create, get, list, update, delete, recent);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllGoals() throws Exception {
        mockMvc.perform(get("/api/goals"))
                .andExpect(status().isOk());
    }

    // Adicione mais testes para POST, GET por id, PATCH, DELETE, exceções, etc.
}
