package com.studiozero.projeto.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientEntityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListAllClients() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindClientByIdNotFound() throws Exception {
        mockMvc.perform(get("/clients/{id}", "invalid-id"))
                .andExpect(status().isBadRequest());
    }

    // Adicione mais testes para POST, PATCH, DELETE, exceções, etc.
}
