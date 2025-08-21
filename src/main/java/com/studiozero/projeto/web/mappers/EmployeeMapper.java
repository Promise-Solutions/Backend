package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.web.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.EmployeeResponseDTO;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDTO dto) {
        if (dto == null)
            return null;
        return new Employee(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getContact(),
                dto.getCpf(),
                dto.getPassword(),
                dto.getActive());
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getContact(),
                employee.getActive());
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
        if (dto == null || id == null)
            return null;
        return new Employee(
                id,
                dto.getName(),
                dto.getEmail(),
                dto.getContact(),
                dto.getCpf(),
                dto.getPassword(),
                dto.getActive());
    }
}
