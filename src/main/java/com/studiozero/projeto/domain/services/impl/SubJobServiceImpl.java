package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.SubJobRepository;
import com.studiozero.projeto.domain.services.SubJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubJobServiceImpl implements SubJobService {

    @Autowired
    private SubJobRepository subJobRepository;
}
