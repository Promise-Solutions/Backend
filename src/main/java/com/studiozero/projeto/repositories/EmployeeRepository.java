package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByCpf(String cpf);
    Optional<Employee> findByEmail(String email);
}
