package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.dtos.response.EmployeeResponseDTO;
import com.studiozero.projeto.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setContact(dto.getContact());
        employee.setCpf(dto.getCpf());
        employee.setPassword(dto.getPassword());
        employee.setActive(dto.getActive());
        return employee;
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setCpf(employee.getCpf());
        dto.setEmail(employee.getEmail());
        dto.setContact(employee.getContact());
        dto.setActive(employee.getActive());

        return dto;
    }

    public static List<EmployeeResponseDTO> toListDtos(List<Employee> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public static Employee toEntity(EmployeeUpdateRequestDTO dto, UUID id) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();


        employee.setId(id);
        if (dto.getName() != null) employee.setName(dto.getName());
        if (dto.getEmail() != null) employee.setEmail(dto.getEmail());
        if (dto.getContact() != null) employee.setContact( dto.getContact());
        if (dto.getCpf() != null) employee.setCpf(dto.getCpf());
        if (dto.getPassword() != null) employee.setPassword(dto.getPassword());
        if (dto.getActive() != null) employee.setActive(dto.getActive());

        return employee;
    }
}
