package com.studiozero.projeto.users.infrastruture.repository.jpa;

import com.studiozero.projeto.users.infrastruture.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    EmployeeEntity findByEmail(String email);

    EmployeeEntity findByEmailAndPassword(String email, String password);

    EmployeeEntity findByCpf(String cpf);
}
