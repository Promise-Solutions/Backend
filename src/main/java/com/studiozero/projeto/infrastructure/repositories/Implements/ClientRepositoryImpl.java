package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JpaClientRepository jpaClientRepository;

    @Autowired
    public ClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client findById(UUID id) {
        Optional<Client> client = jpaClientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public void save(Client client) {
        jpaClientRepository.save(client);
    }

    @Override
    public void deleteById(UUID id) {
        jpaClientRepository.deleteById(id);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaClientRepository.existsByCpf(cpf);
    }

    @Override
    public List<Client> listAll() {
        return jpaClientRepository.findAll();
    }
}
