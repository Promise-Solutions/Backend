package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.web.dtos.request.EmployeeRequestDTO;
import com.studiozero.projeto.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.web.dtos.response.EmployeeResponseDTO;
import java.util.List;
import java.util.UUID;

public class EmployeeMapper {
    public static Employee toDomain(EmployeeRequestDTO dto) {
        if (dto == null) return null;
        return new Employee(
            null,
            dto.getName(),
            dto.getEmail(),
            dto.getContact(),
            dto.getCpf(),
            dto.getPassword(),
            dto.getActive()
        );
    }

    public static Employee toDomain(EmployeeUpdateRequestDTO dto, UUID id) {
        if (dto == null || id == null) return null;
        return new Employee(
            id,
            dto.getName(),
            dto.getEmail(),
            dto.getContact(),
            dto.getCpf(),
            dto.getActive()
        );
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        if (employee == null) return null;
        return new EmployeeResponseDTO(
            employee.getId(),
            employee.getName(),
            employee.getCpf(),
            employee.getEmail(),
            employee.getContact(),
            employee.getActive()
        );
    }

    public static List<EmployeeResponseDTO> toDTOList(List<Employee> employees) {
        if (employees == null) return null;
        return employees.stream().map(EmployeeMapper::toDTO).toList();
    }
}
