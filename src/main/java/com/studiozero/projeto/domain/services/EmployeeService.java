package com.studiozero.projeto.domain.services;

import com.studiozero.projeto.applications.web.dtos.request.EmployeeCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.EmployeeUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO save(EmployeeCreateRequestDTO employeeDto);
    EmployeeResponseDTO findById(EmployeeSearchRequestDTO employeeDto);
    EmployeeResponseDTO update(EmployeeUpdateRequestDTO employeeDto);
    List<EmployeeResponseDTO> findAll();
    String delete(EmployeeDeleteRequestDTO employeeDto);
}
