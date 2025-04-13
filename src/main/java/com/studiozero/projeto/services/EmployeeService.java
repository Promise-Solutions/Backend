package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.exceptions.EntityAlreadyExists;
import com.studiozero.projeto.exceptions.EntityNotFoundException;
import com.studiozero.projeto.mappers.EmployeeMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeResponseDTO save(EmployeeRequestDTO employeeDto) {
        if (employeeRepository.findByCpf(employeeDto.getCpf()) != null) {
            throw new EntityAlreadyExists("Employee with this CPF already exists");
        }

        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(savedEmployee);
    }

    public EmployeeResponseDTO findById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    public EmployeeResponseDTO update(UUID id, EmployeeRequestDTO employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Employee existingEmployee = employeeRepository.findByCpf(employeeDto.getCpf());
        if (existingEmployee != null && !existingEmployee.getId().equals(id)) {
            throw new EntityAlreadyExists("Employee with this CPF already exists");
        }

        employee.setName(employeeDto.getName());
        employee.setCpf(employeeDto.getCpf());
        employee.setEmail(employeeDto.getEmail());
        employee.setContact(employeeDto.getContact());
        employee.setActive(employeeDto.getActive());
        employee.setPassword(employeeDto.getPassword());
        employee.setToken(employeeDto.getToken());

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(updatedEmployee);
    }

    public void delete(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }
}
