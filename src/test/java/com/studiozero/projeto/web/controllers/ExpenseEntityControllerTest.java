package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.finance.web.controller.ExpenseController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
public class ExpenseEntityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListAllExpenses() throws Exception {
        mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk());
    }

    // Adicione mais testes para POST, GET por id, PATCH, DELETE, exceções, etc.
}
