package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.ClientRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.application.usecases.client.GetClientUseCase;
import com.studiozero.projeto.application.usecases.client.CreateClientUseCase;
import com.studiozero.projeto.application.usecases.client.ListClientsUseCase;
import com.studiozero.projeto.application.usecases.client.UpdateClientUseCase;
import com.studiozero.projeto.application.usecases.client.DeleteClientUseCase;

@Configuration
public class ClientUseCaseConfig {
    @Bean
    public GetClientUseCase getClientUseCase(ClientRepository clientRepository) {
        return new GetClientUseCase(clientRepository);
    }

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepository clientRepository) {
        return new CreateClientUseCase(clientRepository);
    }

    @Bean
    public ListClientsUseCase listClientsUseCase(ClientRepository clientRepository) {
        return new ListClientsUseCase(clientRepository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepository clientRepository) {
        return new UpdateClientUseCase(clientRepository);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepository clientRepository, CommandRepository commandRepository) {
        return new DeleteClientUseCase(clientRepository, commandRepository);
    }

    @Bean
    public ClientRepositoryImpl clientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        return new ClientRepositoryImpl(jpaClientRepository);
    }
}

