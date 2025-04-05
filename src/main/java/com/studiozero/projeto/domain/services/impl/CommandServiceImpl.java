package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.domain.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    private CommandRepository commandRepository;
}
