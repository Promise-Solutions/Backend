package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.infrastructure.entities.ClientEntity;
import com.studiozero.projeto.infrastructure.mappers.ClientEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaClientRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Cacheable(cacheNames = "clients:byId", key = "#id")
    @Override
    public Client findById(UUID id) {
        return jpaClientRepository.findById(id)
            .map(ClientEntityMapper::toDomain)
            .orElse(null);
    }

    @CacheEvict(cacheNames = "clients:byId", key = "#client.id")
    @Override
    public void save(Client client) {
        ClientEntity entity = ClientEntityMapper.toEntity(client);
        jpaClientRepository.save(entity);
    }


    @CacheEvict(cacheNames = "clients:byId", key = "#id")
    @Override
    public void deleteById(UUID id) {
        jpaClientRepository.deleteById(id);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaClientRepository.existsByCpf(cpf);
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll()
            .stream()
            .map(ClientEntityMapper::toDomain)
            .toList();
    }
}
