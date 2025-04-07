package com.studiozero.projeto.domain.services;

import com.studiozero.projeto.applications.web.dtos.request.ClientCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ClientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientResponseDTO save(ClientCreateRequestDTO clientDto);
    ClientResponseDTO search(UUID id);
    List<ClientResponseDTO> list();
    ClientResponseDTO update(ClientUpdateRequestDTO clientDto);
    String delete(ClientDeleteRequestDTO clientDto);
}
