package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.JobRepository;
import com.studiozero.projeto.domain.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
}
