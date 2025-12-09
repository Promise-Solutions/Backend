package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.enums.ClientType;
import com.studiozero.projeto.application.usecases.client.*;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.repositories.CommandRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientUseCasesTest {
    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final CommandRepository commandRepository = mock(CommandRepository.class);

    private final CreateClientUseCase createClientUseCase = new CreateClientUseCase(clientRepository);
    private final DeleteClientUseCase deleteClientUseCase = new DeleteClientUseCase(clientRepository, commandRepository);
    private final GetClientUseCase getClientUseCase = new GetClientUseCase(clientRepository);
    private final ListClientsUseCase listClientsUseCase = new ListClientsUseCase(clientRepository);
    private final UpdateClientUseCase updateClientUseCase = new UpdateClientUseCase(clientRepository);

    private Client validClient() {
        return new Client(
                null,
                "John Doe",
                "123.456.789-10",
                "john@example.com",
                "11999999999",
                ClientType.SINGLE,
                true,
                LocalDate.of(1990,1,1),
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("CreateClient - Should create client when data is valid and CPF does not exist")
    void createClientSuccessfully() {
        Client client = validClient();
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);

        Client result = createClientUseCase.execute(client);

        assertNotNull(result.getId());
        verify(clientRepository).save(client);
    }

    @Test
    @DisplayName("CreateClient - Should throw exception when client is null")
    void createClientNullError() {
        assertThrows(IllegalArgumentException.class, () -> createClientUseCase.execute(null));
    }

    @Test
    @DisplayName("CreateClient - Should throw exception when CPF already exists")
    void createClientCpfExistsError() {
        Client client = validClient();
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> createClientUseCase.execute(client));
    }

    @Test
    @DisplayName("DeleteClient - Should delete client when no commands exist")
    void deleteClientSuccessfully() {
        UUID id = UUID.randomUUID();
        when(commandRepository.existsByClientId(id)).thenReturn(false);
        when(clientRepository.findById(id)).thenReturn(validClient());

        deleteClientUseCase.execute(id);

        verify(clientRepository).deleteById(id);
    }

    @Test
    @DisplayName("DeleteClient - Should throw exception when client has associated commands")
    void deleteClientHasCommandsError() {
        UUID id = UUID.randomUUID();
        when(commandRepository.existsByClientId(id)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> deleteClientUseCase.execute(id));
    }

    @Test
    @DisplayName("DeleteClient - Should throw exception when client is not found")
    void deleteClientNotFoundError() {
        UUID id = UUID.randomUUID();
        when(commandRepository.existsByClientId(id)).thenReturn(false);
        when(clientRepository.findById(id)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> deleteClientUseCase.execute(id));
    }

    @Test
    @DisplayName("GetClient - Should return client when ID exists")
    void getClientSuccessfully() {
        UUID id = UUID.randomUUID();
        Client client = validClient();

        when(clientRepository.findById(id)).thenReturn(client);

        Client result = getClientUseCase.execute(id);

        assertEquals(client, result);
    }

    @Test
    @DisplayName("GetClient - Should throw exception when ID is null")
    void getClientNullIdError() {
        assertThrows(NullPointerException.class, () -> getClientUseCase.execute(null));
    }

    @Test
    @DisplayName("GetClient - Should throw exception when client not found")
    void getClientNotFoundError() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> getClientUseCase.execute(id));
    }

    @Test
    @DisplayName("ListClients - Should return clients list")
    void listClientsSuccessfully() {
        List<Client> clients = List.of(validClient(), validClient());
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = listClientsUseCase.execute();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("UpdateClient - Should update client when valid")
    void updateClientSuccessfully() {
        Client client = validClient();
        UUID id = UUID.randomUUID();

        client.setId(id);
        when(clientRepository.findById(id)).thenReturn(client);

        Client result = updateClientUseCase.execute(client);

        verify(clientRepository).save(client);
        assertEquals(client, result);
    }

    @Test
    @DisplayName("UpdateClient - Should throw exception when client is null")
    void updateClientNullError() {
        assertThrows(IllegalArgumentException.class, () -> updateClientUseCase.execute(null));
    }

    @Test
    @DisplayName("UpdateClient - Should throw exception when ID is invalid")
    void updateClientInvalidIdError() {
        Client client = validClient();
        client.setId(null);

        assertThrows(IllegalArgumentException.class, () -> updateClientUseCase.execute(client));
    }

    @Test
    @DisplayName("UpdateClient - Should throw exception when client does not exist")
    void updateClientNotFoundError() {
        Client client = validClient();
        UUID id = UUID.randomUUID();
        client.setId(id);

        when(clientRepository.findById(id)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> updateClientUseCase.execute(client));
    }
}
