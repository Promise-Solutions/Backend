package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.domain.services.CommandProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProductServiceImpl implements CommandProductService {

    @Autowired
    private CommandProductRepository commandProductRepository;
}
