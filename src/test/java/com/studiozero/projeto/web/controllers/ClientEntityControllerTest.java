package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.client.CreateClientUseCase;
import com.studiozero.projeto.application.usecases.client.DeleteClientUseCase;
import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.client.ListClientsUseCase;
import com.studiozero.projeto.application.usecases.client.UpdateClientUseCase;
import com.studiozero.projeto.domain.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // minimal real implementations for use cases that satisfy controller dependencies
        CreateClientUseCase create = new CreateClientUseCase(null) {
            @Override
            public Client execute(Client client) {
                return client; // no-op create
            }
        };

        GetClientUseCase get = new GetClientUseCase(null) {
            @Override
            public Client execute(UUID id) {
                throw new IllegalArgumentException("client not found"); // simulate not found -> handled as 400
            }
        };

        ListClientsUseCase list = new ListClientsUseCase(null) {
            @Override
            public List<Client> execute() {
                return List.of(); // empty list
            }
        };

        UpdateClientUseCase update = new UpdateClientUseCase(null) {
            @Override
            public Client execute(Client client) {
                return client; // no-op update
            }
        };

        DeleteClientUseCase delete = new DeleteClientUseCase(null, null) {
            @Override
            public void execute(UUID id) {
                // no-op delete
            }
        };

        ClientController controller = new ClientController(create, get, list, update, delete);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllClients() throws Exception {
        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindClientByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/{id}", "11111111-1111-1111-1111-111111111111"))
                .andExpect(status().isBadRequest());
    }

    // Adicione mais testes para POST, PATCH, DELETE, exceções, etc.
}
