package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaEmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    @Autowired
    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Employee findById(UUID id) {
        return jpaEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee findByEmail(String email) {
        return jpaEmployeeRepository.findByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaEmployeeRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaEmployeeRepository.existsByEmail(email);
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) {
        return jpaEmployeeRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void save(Employee employee) {
        jpaEmployeeRepository.save(employee);
    }

    @Override
    public void deleteById(UUID id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> listAll() {
        return jpaEmployeeRepository.findAll();
    }
}
