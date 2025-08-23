package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.entities.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeEntityMapper {

    public static EmployeeEntity toEntity(Employee employee) {
        if (employee == null)
            return null;
        return new EmployeeEntity(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getContact(),
                employee.getCpf(),
                employee.getPassword(),
                employee.getActive()
        );
    }

    public static Employee toDomain(EmployeeEntity employeeEntity) {
        if (employeeEntity == null)
            return null;
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getEmail(),
                employeeEntity.getContact(),
                employeeEntity.getCpf(),
                employeeEntity.getPassword(),
                employeeEntity.getActive()
        );
    }

    public static List<Employee> toDomainList(List<EmployeeEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(EmployeeEntityMapper::toDomain).toList();
    }

    public static List<EmployeeEntity> toEntityList(List<Employee> employees) {
        if (employees == null) return null;
        return employees.stream().map(EmployeeEntityMapper::toEntity).toList();
    }
}
