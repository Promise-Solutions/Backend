package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    EmployeeEntity findByEmail(String email);

    EmployeeEntity findByEmailAndPassword(String email, String password);
}
