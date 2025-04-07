package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.applications.web.dtos.request.EmployeeCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.domain.exceptions.EntityAlreadyExists;
import com.studiozero.projeto.domain.exceptions.EntityNotFoundException;
import com.studiozero.projeto.domain.repositories.EmployeeRepository;
import com.studiozero.projeto.domain.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO save(EmployeeCreateRequestDTO employeeDto) {
        if (employeeRepository.findByCpf(employeeDto.getCpf()) != null) {
            throw new EntityAlreadyExists("Employee with this CPF already exists");
        }

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setCpf(employeeDto.getCpf());
        employee.setEmail(employeeDto.getEmail());
        employee.setContact(employeeDto.getContact());
        employee.setActive(employeeDto.getActive());
        employee.setPassword(employeeDto.getPassword());
        employee.setToken(employeeDto.getToken());

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO findById(EmployeeSearchRequestDTO employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return new EmployeeResponseDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO update(EmployeeUpdateRequestDTO employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employeeRepository.findByCpf(employeeDto.getCpf()) != null) {
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

        return new EmployeeResponseDTO(updatedEmployee);
    }

    @Override
    public String delete(EmployeeDeleteRequestDTO employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
        return "Employee deleted successfully";
    }
}
