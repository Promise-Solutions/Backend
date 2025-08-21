package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaEmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Employee findByEmail(String email);

    Employee findByEmailAndPassword(String email, String password);
}
