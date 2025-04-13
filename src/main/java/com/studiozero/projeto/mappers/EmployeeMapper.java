package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setContact(dto.getContact());
        employee.setCpf(dto.getCpf());
        employee.setPassword(dto.getPassword());
        employee.setToken(dto.getToken());
        employee.setActive(dto.getActive());
        return employee;
    }

    public EmployeeResponseDTO toDTO(Employee employee) {
        return new EmployeeResponseDTO(employee);
    }
}
