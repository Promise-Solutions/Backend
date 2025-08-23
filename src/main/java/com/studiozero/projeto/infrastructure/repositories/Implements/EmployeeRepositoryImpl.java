package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.infrastructure.entities.EmployeeEntity;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaEmployeeRepository;
import com.studiozero.projeto.infrastructure.mappers.EmployeeEntityMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    @Override
    public Employee findById(UUID id) {
        return jpaEmployeeRepository.findById(id)
            .map(EmployeeEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll()
            .stream()
            .map(EmployeeEntityMapper::toDomain)
            .toList();
    }

    @Override
    public Employee findByEmail(String email) {
        return EmployeeEntityMapper.toDomain(jpaEmployeeRepository.findByEmail(email));
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
        return EmployeeEntityMapper.toDomain(jpaEmployeeRepository.findByEmailAndPassword(email, password));
    }

    @Override
    public void save(Employee employee) {
        EmployeeEntity entity = EmployeeEntityMapper.toEntity(employee);
        jpaEmployeeRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaEmployeeRepository.deleteById(id);
    }

}
