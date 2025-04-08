package com.studiozero.projeto.services;

import com.studiozero.projeto.repositories.CommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProductService {

    @Autowired
    private CommandProductRepository commandProductRepository;
}
