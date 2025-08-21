package com.studiozero.projeto.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GoalController.class)
public class GoalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListAllGoals() throws Exception {
        mockMvc.perform(get("/goals"))
                .andExpect(status().isOk());
    }

    // Adicione mais testes para POST, GET por id, PATCH, DELETE, exceções, etc.
}
