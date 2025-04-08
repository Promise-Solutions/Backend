package com.studiozero.projeto.services;

import com.studiozero.projeto.repositories.SubJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubJobService {

    @Autowired
    private SubJobRepository subJobRepository;
}
