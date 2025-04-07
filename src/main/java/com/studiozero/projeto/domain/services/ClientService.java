package com.studiozero.projeto.domain.services;

import com.studiozero.projeto.applications.web.dtos.request.ClientCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientSearchRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ClientUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ClientResponseDTO;

import java.util.List;

public interface ClientService {
    ClientResponseDTO save(ClientCreateRequestDTO clientDto);
    ClientResponseDTO search(ClientSearchRequestDTO clientDto);
    List<ClientResponseDTO> list();
    ClientResponseDTO update(ClientUpdateRequestDTO clientDto);
    String delete(ClientDeleteRequestDTO clientDto);
}
