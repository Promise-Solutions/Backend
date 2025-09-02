package com.studiozero.projeto.customers.domain;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    Client findById(UUID id);

    void save(Client client);

    void deleteById(UUID id);

    boolean existsByCpf(String cpf);

    List<Client> findAll();
}