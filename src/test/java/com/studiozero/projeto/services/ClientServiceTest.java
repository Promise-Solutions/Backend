package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.ClientService;
import com.studiozero.projeto.application.services.TracingService;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Tracing;
import com.studiozero.projeto.application.enums.ClientType;
import com.studiozero.projeto.application.enums.Context;
import com.studiozero.projeto.infrastructure.exceptions.BadRequestException;
import com.studiozero.projeto.infrastructure.exceptions.ConflictException;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.repositories.CommandRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CommandRepository commandRepository;

    @Mock
    private TracingService tracingService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client(
                UUID.randomUUID(),
                "Test User",
                "12345678900",
                "test@example.com",
                "999999999",
                ClientType.SINGLE,
                true,
                LocalDate.of(1990, 1, 1),
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("Should create client successfully when CPF does not exist")
    void createClient_Success() {
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(tracingService.setTracing(Context.USER)).thenReturn(new Tracing());

        Client savedClient = clientService.createClient(client);

        assertNotNull(savedClient.getId());
        assertNotNull(savedClient.getCreatedDate());

        verify(tracingService).setTracing(Context.USER);
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    @DisplayName("Should throw ConflictException when creating client with existing CPF")
    void createClient_ThrowsConflictException() {
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

        assertThrows(ConflictException.class, () -> clientService.createClient(client));

        verify(clientRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should find client by ID successfully")
    void findClientById_Success() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client found = clientService.findClientById(client.getId());

        assertEquals(client, found);
    }

    @Test
    @DisplayName("Should throw NotFoundException when client ID does not exist")
    void findClientById_NotFound() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.findClientById(client.getId()));
    }

    @Test
    @DisplayName("Should return list of all clients")
    void listClients_ReturnsList() {
        List<Client> clients = Arrays.asList(client, client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.listClients();

        assertEquals(2, result.size());
        assertEquals(clients, result);
    }

    @Test
    @DisplayName("Should update client successfully when client exists")
    void updateClient_Success() {
        when(clientRepository.existsById(client.getId())).thenReturn(true);
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(tracingService.setTracing(Context.USER)).thenReturn(new Tracing());

        Client updated = clientService.updateClient(client);

        assertEquals(client.getId(), updated.getId());
        verify(tracingService).setTracing(Context.USER);
        verify(clientRepository).save(client);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existing client")
    void updateClient_NotFound() {
        when(clientRepository.existsById(client.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> clientService.updateClient(client));

        verify(clientRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete client successfully when no associated commands exist")
    void deleteClient_Success() {
        UUID clientId = client.getId();
        when(commandRepository.existsByClient_Id(clientId)).thenReturn(false);
        when(clientRepository.existsById(clientId)).thenReturn(true);
        when(tracingService.setTracing(Context.USER)).thenReturn(new Tracing());

        clientService.deleteClient(clientId);

        verify(tracingService).setTracing(Context.USER);
        verify(clientRepository).deleteById(clientId);
    }

    @Test
    @DisplayName("Should throw BadRequestException when deleting client with associated commands")
    void deleteClient_WithAssociatedCommands() {
        UUID clientId = client.getId();
        when(commandRepository.existsByClient_Id(clientId)).thenReturn(true);

        assertThrows(BadRequestException.class, () -> clientService.deleteClient(clientId));

        verify(clientRepository, never()).deleteById(any());
        verify(tracingService, never()).setTracing(any());
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existing client")
    void deleteClient_NotFound() {
        UUID clientId = client.getId();
        when(commandRepository.existsByClient_Id(clientId)).thenReturn(false);
        when(clientRepository.existsById(clientId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> clientService.deleteClient(clientId));

        verify(clientRepository, never()).deleteById(any());
        verify(tracingService, never()).setTracing(any());
    }
}
