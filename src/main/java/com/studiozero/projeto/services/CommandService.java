package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.dtos.response.CommandResponseDTO;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.CommandMapper;
import com.studiozero.projeto.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private CommandMapper commandMapper;

    public CommandResponseDTO save(CommandRequestDTO commandDto) {
        Command command = commandMapper.toEntity(commandDto);
        Command savedCommand = commandRepository.save(command);
        return commandMapper.toDTO(savedCommand);
    }

    public CommandResponseDTO findById(Integer id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Command not found"));
        return commandMapper.toDTO(command);
    }

    public List<CommandResponseDTO> findAll() {
        return commandRepository.findAll().stream()
                .map(commandMapper::toDTO)
                .toList();
    }

    public CommandResponseDTO update(Integer id, CommandRequestDTO commandDto) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Command not found"));

        command.setOpeningDateTime(commandDto.getOpeningDateTime());
        command.setClosingDateTime(commandDto.getClosingDateTime());
        command.setDiscount(commandDto.getDiscount());
        command.setTotalValue(commandDto.getTotalValue());
        command.setFkClient(commandDto.getFkClient());
        command.setFkEmployee(commandDto.getFkEmployee());
        command.setStatus(commandDto.getStatus());

        Command updatedCommand = commandRepository.save(command);
        return commandMapper.toDTO(updatedCommand);
    }

    public void delete(Integer id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Command not found"));
        commandRepository.delete(command);
    }
}
