package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.CommandProductMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandProductService {

    @Autowired
    private CommandProductRepository commandProductRepository;

    @Autowired
    private CommandProductMapper commandProductMapper;

    @Autowired
    private ClientRepository clientRepository;

    public CommandProductResponseDTO save(CommandProductRequestDTO commandProductDto) {
        CommandProduct commandProduct = commandProductMapper.toEntity(commandProductDto);

        CommandProduct savedCommandProduct = commandProductRepository.save(commandProduct);

        return commandProductMapper.toDTO(savedCommandProduct);
    }

    public CommandProductResponseDTO findById(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CommandProduct not found"));
        return commandProductMapper.toDTO(commandProduct);
    }

    public List<CommandProductResponseDTO> findAll() {
        return commandProductRepository.findAll().stream()
                .map(commandProductMapper::toDTO)
                .toList();
    }

    public CommandProductResponseDTO update(Integer id, CommandProductRequestDTO commandProductDto) {
        CommandProduct commandProduct = commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("commandProduct not found"));

        commandProduct.setFkProduct(commandProductDto.getFkProduct());
        commandProduct.setFkCommand(commandProductDto.getFkCommand());
        commandProduct.setProductQuantity(commandProductDto.getProductQuantity());
        commandProduct.setUnitValue(commandProductDto.getUnitValue());

        CommandProduct updatedCommandProduct = commandProductRepository.save(commandProduct);

        return commandProductMapper.toDTO(updatedCommandProduct);
    }

    public void delete(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CommandProduct not found"));
        commandProductRepository.delete(commandProduct);
    }
}
