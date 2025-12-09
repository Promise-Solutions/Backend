package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import com.studiozero.projeto.infrastructure.mappers.ClientEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.ClientRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientRepositoryImplTest {

    private JpaClientRepository jpaClientRepository;
    private ClientRepositoryImpl clientRepository;

    @BeforeEach
    void setUp() {
        jpaClientRepository = mock(JpaClientRepository.class);
        clientRepository = new ClientRepositoryImpl(jpaClientRepository);
    }

    @Test
    void testFindByIdFound() {
        UUID id = UUID.randomUUID();
        ClientEntity entity = mock(ClientEntity.class);
        Client client = mock(Client.class);

        when(jpaClientRepository.findById(id)).thenReturn(Optional.of(entity));
        try (var mocked = mockStatic(ClientEntityMapper.class)) {
            mocked.when(() -> ClientEntityMapper.toDomain(entity)).thenReturn(client);
            Client result = clientRepository.findById(id);
            assertEquals(client, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaClientRepository.findById(id)).thenReturn(Optional.empty());
        Client result = clientRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Client client = mock(Client.class);
        ClientEntity entity = mock(ClientEntity.class);

        try (var mocked = mockStatic(ClientEntityMapper.class)) {
            mocked.when(() -> ClientEntityMapper.toEntity(client)).thenReturn(entity);
            clientRepository.save(client);
            verify(jpaClientRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        clientRepository.deleteById(id);
        verify(jpaClientRepository).deleteById(id);
    }

    @Test
    void testExistsByCpf() {
        String cpf = "12345678900";
        when(jpaClientRepository.existsByCpf(cpf)).thenReturn(true);
        assertTrue(clientRepository.existsByCpf(cpf));
    }

    @Test
    void testFindAll() {
        ClientEntity entity1 = mock(ClientEntity.class);
        ClientEntity entity2 = mock(ClientEntity.class);
        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);

        when(jpaClientRepository.findAll()).thenReturn(List.of(entity1, entity2));
        try (var mocked = mockStatic(ClientEntityMapper.class)) {
            mocked.when(() -> ClientEntityMapper.toDomain(entity1)).thenReturn(client1);
            mocked.when(() -> ClientEntityMapper.toDomain(entity2)).thenReturn(client2);
            List<Client> result = clientRepository.findAll();
            assertEquals(List.of(client1, client2), result);
        }
    }
}