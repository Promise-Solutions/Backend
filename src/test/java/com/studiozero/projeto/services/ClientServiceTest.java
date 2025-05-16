package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.exceptions.BadRequestException;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CommandRepository commandRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should create a client successfully when CPF does not exist")
    void shouldCreateClientSuccessfullyWhenCpfDoesNotExist() {
        Client client = new Client();
        client.setCpf("12345678900");

        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient.getId());
        assertEquals(LocalDate.now(), createdClient.getCreatedDate());
        verify(clientRepository).save(client);
    }

    @Test
    @DisplayName("should throw ConflictException when CPF already exists")
    void shouldThrowConflictExceptionWhenCpfAlreadyExists() {
        Client client = new Client();
        client.setCpf("12345678900");

        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

        assertThrows(ConflictException.class, () -> clientService.createClient(client));
        verify(clientRepository, never()).save(any());
    }

    @Test
    @DisplayName("should throw BadRequestException when CPF is invalid")
    void shouldReturnClientWhenIdExists() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client foundClient = clientService.findClientById(clientId);

        assertEquals(clientId, foundClient.getId());
        verify(clientRepository).findById(clientId);
    }

    @Test
    @DisplayName("should throw NotFoundException when client ID does not exist")
    void shouldThrowNotFoundExceptionWhenIdDoesNotExist() {
        UUID clientId = UUID.randomUUID();

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.findClientById(clientId));
        verify(clientRepository).findById(clientId);
    }

    @Test
    @DisplayName("should return list of clients")
    void shouldReturnListOfClients() {
        Client client1 = new Client();
        client1.setId(UUID.randomUUID());
        client1.setCpf("11111111111");

        Client client2 = new Client();
        client2.setId(UUID.randomUUID());
        client2.setCpf("22222222222");

        List<Client> clients = List.of(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.listClients();

        assertEquals(2, result.size());
        assertEquals(clients, result);
        verify(clientRepository).findAll();
    }

    @Test
    @DisplayName("should return empty list when no clients exist")
    void shouldReturnEmptyListWhenNoClientsExist() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> result = clientService.listClients();

        assertTrue(result.isEmpty());
        verify(clientRepository).findAll();
    }

    @Test
    @DisplayName("should update client when ID exists")
    void shouldUpdateClientWhenIdExists() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);
        client.setCpf("12345678900");

        when(clientRepository.existsById(clientId)).thenReturn(true);
        when(clientRepository.save(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(client);

        assertEquals(clientId, updatedClient.getId());
        verify(clientRepository).existsById(clientId);
        verify(clientRepository).save(client);
    }

    @Test
    @DisplayName("should throw NotFoundException when client ID does not exist")
    void shouldThrowNotFoundExceptionWhenClientIdDoesNotExist() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);

        when(clientRepository.existsById(clientId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> clientService.updateClient(client));
        verify(clientRepository).existsById(clientId);
        verify(clientRepository, never()).save(any());
    }

    @Test
    @DisplayName("should throw BadRequestException when client ID is invalid")
    void shouldThrowBadRequestExceptionWhenClientHasAssociatedCommands() {
        UUID clientId = UUID.randomUUID();

        when(commandRepository.existsByClient_Id(clientId)).thenReturn(true);

        assertThrows(BadRequestException.class, () -> clientService.deleteClient(clientId));
        verify(commandRepository).existsByClient_Id(clientId);
        verify(clientRepository, never()).existsById(any());
        verify(clientRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("should delete client when client exists and has no associated commands")
    void shouldDeleteClientWhenClientExistsAndNoAssociatedCommands() {
        UUID clientId = UUID.randomUUID();

        when(commandRepository.existsByClient_Id(clientId)).thenReturn(false);
        when(clientRepository.existsById(clientId)).thenReturn(true);

        clientService.deleteClient(clientId);

        verify(commandRepository).existsByClient_Id(clientId);
        verify(clientRepository).existsById(clientId);
        verify(clientRepository).deleteById(clientId);
    }

    @Test
    @DisplayName("should throw NotFoundException when client does not exist and has no associated commands")
    void shouldThrowNotFoundExceptionWhenClientDoesNotExistAndNoAssociatedCommands() {
        UUID clientId = UUID.randomUUID();

        when(commandRepository.existsByClient_Id(clientId)).thenReturn(false);
        when(clientRepository.existsById(clientId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> clientService.deleteClient(clientId));
        verify(commandRepository).existsByClient_Id(clientId);
        verify(clientRepository).existsById(clientId);
        verify(clientRepository, never()).deleteById(any());
    }
}