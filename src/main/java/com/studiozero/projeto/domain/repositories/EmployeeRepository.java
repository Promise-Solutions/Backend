package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository {
    Employee findById(UUID id);

    Employee findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Employee findByEmailAndPassword(String email, String password);

    void save(Employee employee);

    void deleteById(UUID id);

    List<Employee> findAll();

    Employee findByCpf(String cpf);
}
