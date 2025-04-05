package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.ClientRepository;
import com.studiozero.projeto.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

}
