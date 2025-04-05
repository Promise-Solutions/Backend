package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.domain.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
}
