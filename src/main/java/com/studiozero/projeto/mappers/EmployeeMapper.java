package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.EmployeeRequestDTO;
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
        return new EmployeeResponseDTO(employee);
    }

    public static List<EmployeeResponseDTO> toListDtos(List<Employee> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public static Employee toEntity(EmployeeRequestDTO dto, UUID id) {
        if (dto == null) {
            return null;
        }
        return new Employee(
                id,
                dto.getName(),
                dto.getEmail(),
                dto.getContact(),
                dto.getCpf(),
                dto.getPassword(),
                dto.getActive()
        );
    }
}
