package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.entities.*;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.CommandRepository;
import com.studiozero.projeto.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CommandProductMapper {
    private final CommandRepository commandRepository;
    private final ProductRepository productRepository;

    public CommandProductMapper(CommandRepository commandRepository, ProductRepository productRepository) {
        this.commandRepository = commandRepository;
        this.productRepository = productRepository;
    }

    public CommandProduct toEntity(CommandProductRequestDTO dto) {
        Command command = commandRepository.findById(dto.getFkCommand())
                .orElseThrow(() -> new NotFoundException("FkCommand Not Found"));

        Product product = productRepository.findById(dto.getFkProduct())
                .orElseThrow(() -> new NotFoundException("FkProduct Not found"));

        CommandProduct commandProduct = new CommandProduct();
        commandProduct.setCommand(command);
        commandProduct.setProduct(product);
        commandProduct.setProductQuantity(dto.getProductQuantity());
        commandProduct.setUnitValue(dto.getUnitValue());
        return commandProduct;
    }

    public static CommandProductResponseDTO toDTO(CommandProduct commandProduct) {
        return new CommandProductResponseDTO(commandProduct);
    }

    public static List<CommandProductResponseDTO> toListDtos(List<CommandProduct> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(CommandProductMapper::toDTO)
                .toList();
    }

    public CommandProduct toEntity(CommandProductRequestDTO dto, Integer id) {
        if (dto == null) {
            return null;
        }

        Command command = commandRepository.findById(dto.getFkCommand())
                .orElseThrow(() -> new NotFoundException("FkCommand not found!"));

        Product product = productRepository.findById(dto.getFkProduct())
                .orElseThrow(() -> new NotFoundException("FkProduto not found!"));


        CommandProduct commandProduct = new CommandProduct();

        commandProduct.setId(id);
        commandProduct.setProductQuantity(dto.getProductQuantity());
        commandProduct.setUnitValue(dto.getUnitValue());
        commandProduct.setCommand(command);
        commandProduct.setProduct(product);
        return commandProduct;
    }
}
