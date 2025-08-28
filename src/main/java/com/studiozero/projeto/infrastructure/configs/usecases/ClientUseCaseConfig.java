package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.ClientRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.client.CreateClientUseCase;
import com.studiozero.projeto.application.usecases.client.ListClientsUseCase;
import com.studiozero.projeto.application.usecases.client.UpdateClientUseCase;
import com.studiozero.projeto.application.usecases.client.DeleteClientUseCase;

@Configuration
public class ClientUseCaseConfig {
    @Bean
    public GetClientUseCase getClientUseCase(ClientRepositoryImpl clientRepository) {
        return new GetClientUseCase(clientRepository);
    }

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryImpl clientRepository) {
        return new CreateClientUseCase(clientRepository);
    }

    @Bean
    public ListClientsUseCase listClientsUseCase(ClientRepositoryImpl clientRepository) {
        return new ListClientsUseCase(clientRepository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepositoryImpl clientRepository) {
        return new UpdateClientUseCase(clientRepository);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepositoryImpl clientRepository, CommandRepository commandRepository) {
        return new DeleteClientUseCase(clientRepository, commandRepository);
    }
}

